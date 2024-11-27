package com.educacional.educacional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaRequestDTO(

        @NotBlank(message = "O nome da disciplina é obrigatório")
        String nome,

        @NotBlank(message = "O código da disciplina é obrigatório")
        String codigo,

        @NotNull(message = "O ID do curso é obrigatório")
        Integer curso_id,

        @NotNull(message = "O ID do professor é obrigatório")
        Integer professor_id

) {
}
