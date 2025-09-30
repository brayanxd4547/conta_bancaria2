package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;

import java.util.List;

public record ClienteResponseDto(
        String id,
        String nome,
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
