package com.educacional.educacional.dto;

import jakarta.validation.constraints.NotNull;

public record TurmaRequestDTO(
        @NotNull(message = "O ano é obrigatório") Integer ano,

        @NotNull(message = "O semestre é obrigatório") Integer semestre) {
}
