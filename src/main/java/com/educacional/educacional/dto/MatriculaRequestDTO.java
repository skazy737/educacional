package com.educacional.educacional.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequestDTO(
        @NotNull(message = "O ID do aluno é obrigatório") Integer aluno_id,
        @NotNull(message = "O ID da turma é obrigatório") Integer turma_id) {
}
