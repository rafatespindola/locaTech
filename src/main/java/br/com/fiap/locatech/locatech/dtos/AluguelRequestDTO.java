package br.com.fiap.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @NotNull(message = "O id da pessoa não pode ser null")
        Long pessoaId,
        @NotNull(message = "O id do veículo não pode ser null")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
    ) {
}