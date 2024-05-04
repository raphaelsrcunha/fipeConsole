package com.raphaelsrcunha.fipi.principal;

import com.raphaelsrcunha.fipi.model.Dados;
import com.raphaelsrcunha.fipi.model.Modelos;
import com.raphaelsrcunha.fipi.model.Vehicle;
import com.raphaelsrcunha.fipi.service.ConsumerAPI;
import com.raphaelsrcunha.fipi.service.DataConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// cod marca -> trecho nome veiculo -> cod modelo -> retorno valores por ano
public class Principal {

    private Scanner read = new Scanner(System.in);
    private ConsumerAPI consumerAPI = new ConsumerAPI();
    private DataConverter converter = new DataConverter();

    private final String url = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {

        System.out.println("Gostaria de pesquisar valores de carros, motos ou caminhões?:");
        var typeVehicle = read.nextLine();

        if (typeVehicle.contains("carr")){
            typeVehicle = "carros";
        } else if (typeVehicle.contains("mot")) {
            typeVehicle = "motos";
        } else{
            typeVehicle = "caminhoes";
        }

        var urlMarcas = url + typeVehicle + "/marcas/";
        var json = consumerAPI.getData(urlMarcas);
        System.out.println(json);

        var brands = converter.getList(json, Dados.class);

        brands.forEach(dados -> System.out.println("\nCódigo: " + dados.cod() +
                "\nMarca: " + dados.name()));

        System.out.println("Digite o código da marca que deseja pesquisar: ");
        var brandCode = read.nextLine();

        var urlModelos = urlMarcas + brandCode + "/modelos/";
        var jsonModelos = consumerAPI.getData(urlModelos);

        var modelsList = converter.getData(jsonModelos, Modelos.class);

        System.out.println("\nModelos dessa marca:");
        modelsList.modelos().stream()
                .sorted(Comparator.comparing(Dados::cod))
                .forEach(modelo -> System.out.println("\nCódigo: " + modelo.cod() + "\nNome: " + modelo.name()));

        System.out.println("\nEscreva um trecho do nome do carro que deseja obter o valor: ");
        String carName = read.nextLine();

        List<Dados> modelsListFiltered = modelsList.modelos().stream()
                .filter(m -> m.name().toLowerCase().contains(carName.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos do carro digitado:");
        modelsListFiltered.forEach(m -> System.out.println("\nCódigo: " + m.cod() +
                "\nNome: " + m.name()));

        System.out.println("\nDigite o código do modelo para obter os anos de fabricação desse modelo: ");
        String carCod = read.nextLine();

        String urlAnos = urlModelos + carCod + "/anos/";

        String jsonAnos = consumerAPI.getData(urlAnos);

        var anosList = converter.getList(jsonAnos, Dados.class);

        List<Vehicle> vehiclesList = new ArrayList<>();

        for(int i = 0; i < anosList.size(); i++){
            var urlAno = urlAnos + anosList.get(i).cod();
            String jsonFinal = consumerAPI.getData(urlAno);
            Vehicle vehicle = converter.getData(jsonFinal, Vehicle.class);
            vehiclesList.add(vehicle);
        }

        System.out.println("\nLista de anos do veículo selecionado");
        vehiclesList.forEach(vehicle -> System.out.println("\nModelo: " + vehicle.Modelo() +
                "\nMarca: " + vehicle.Marca() +
                "\nValor: " + vehicle.Valor() +
                "\nAno: " + vehicle.AnoModelo() +
                "\nCombustível: " + vehicle.Combustivel() +
                "\nCódigo FIPE: " + vehicle.CodigoFipe()));





//        System.out.println("\nDigite agora o código do ano desejado:");
//        String anoModelo = read.nextLine();
//
//        String urlFinal = urlAnos + anoModelo + "/";
//
//        String jsonFinal = consumerAPI.getData(urlFinal);
//
//        System.out.println("\nInformações sobre veículo escolhido:");
//        Vehicle vehicle = converter.getData(jsonFinal, Vehicle.class);
//        System.out.println("\nModelo: " + vehicle.Modelo() +
//                "\nMarca: " + vehicle.Marca() +
//                "\nValor: " + vehicle.Valor() +
//                "\nAno: " + vehicle.AnoModelo() +
//                "\nCombustível: " + vehicle.Combustivel() +
//                "\nCódigo FIPE: " + vehicle.CodigoFipe());
    }

}





















