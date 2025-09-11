package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;

import java.util.ArrayList;
import java.util.List;

public record ClienteDto(
        String id,
        String nome,
        Long cpf,
        List<Conta> contas
) {
    public static ClienteDto fromEntity(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas() != null ? cliente.getContas() : List.of()
        );
    }

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setContas(contas != null ? new ArrayList<>(contas) : new ArrayList<>());
        return cliente;
    }
}
