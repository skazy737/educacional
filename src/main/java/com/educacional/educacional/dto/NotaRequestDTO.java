package com.educacional.educacional.dto;

import java.time.LocalDate;

public record NotaRequestDTO(
        Double nota,
        LocalDate dataLancamento) {

        public NotaRequestDTO {
                if (nota == null) {
                        throw new IllegalArgumentException("A nota é obrigatória");
                }
                if (dataLancamento == null) {
                        throw new IllegalArgumentException("A data de lançamento é obrigatória");
                }
        }
}
