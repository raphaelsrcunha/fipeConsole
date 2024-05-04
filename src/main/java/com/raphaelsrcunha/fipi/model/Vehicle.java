package com.raphaelsrcunha.fipi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(String Modelo,
                      String Marca,
                      String Valor,
                      String AnoModelo,
                      String Combustivel,
                      String CodigoFipe
                      ) {
}
