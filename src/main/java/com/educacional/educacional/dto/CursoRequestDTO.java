package com.educacional.educacional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CursoRequestDTO(


        @NotBlank(message = "O nome do curso é obrigatório")
        String nome,


        @NotBlank(message = "O código do curso é obrigatório")
        String codigo,


        @NotNull(message = "A carga horária é obrigatória")
        @Positive(message = "A carga horária deve ser um valor positivo")
        Integer cargaHoraria
) {
}

