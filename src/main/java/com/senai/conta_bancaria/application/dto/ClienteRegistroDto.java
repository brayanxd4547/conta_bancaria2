package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record ClienteRegistroDto(
        String nome,
        Long cpf,
        ContaResumoDto contaDto
) {
    public static ClienteRegistroDto fromEntity(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteRegistroDto(
                cliente.getNome(),
                cliente.getCpf(),
                null
        );
    }

    public Cliente toEntity() {
        return Cliente.builder()
                .status(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<Conta>())
                .build();
    }
}
