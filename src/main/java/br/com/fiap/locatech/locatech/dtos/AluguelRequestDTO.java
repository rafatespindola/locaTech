package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @Schema(description = "Id da pessoa que aluga o veículo")
        @NotNull(message = "O id da pessoa não pode ser null")
        Long pessoaId,
        @NotNull(message = "O id do veículo não pode ser null")
        @Schema(description = "Id do veículo que está sendo alugado")
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFim
    ) {
}