package com.raphaelsrcunha.fipi.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados(@JsonAlias("codigo") String cod,
                    @JsonAlias("nome") String name) {
}
