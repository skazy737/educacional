package com.educacional.educacional.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDTO(
        @NotNull(message = "O nome é obrigatório") @NotBlank(message = "O nome não pode estar em branco") String nome,

        @NotNull(message = "O email é obrigatório")
        @Email(message = "O email fornecido não é válido") String email,

        @NotNull(message = "O telefone é obrigatório") @NotBlank(message = "O telefone não pode estar em branco") String telefone,

        @NotNull(message = "A especialidade é obrigatória") @NotBlank(message = "A especialidade não pode estar em branco") String especialidade) {
}
