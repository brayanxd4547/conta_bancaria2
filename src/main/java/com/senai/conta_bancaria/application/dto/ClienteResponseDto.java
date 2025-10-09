package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClienteResponseDto(
        String id,

        @NotBlank(message = "O nome não pode ser vazio.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @Positive(message = "O CPF não pode ser negativo.")
        @Max(value = 99999999999L, message = "O CPF deve ter até 11 digitos.")
        Long cpf,

        List<ContaResumoDto> contas
) {
    public static ClienteResponseDto fromEntity(Cliente cliente) {
        List<ContaResumoDto> contas = cliente
                .getContas()
                .stream()
                .map(ContaResumoDto::fromEntity)
                .toList();
        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                contas
        );
    }
}