package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaPoupancaDto(
        String id,
        Long numero,
        BigDecimal saldo,
        BigDecimal rendimento
) {
    public static ContaPoupancaDto fromEntity(ContaPoupanca contaPoupanca) {
        if (contaPoupanca == null) return null;
        return new ContaPoupancaDto(
                contaPoupanca.getId(),
                contaPoupanca.getNumero(),
                contaPoupanca.getSaldo(),
                contaPoupanca.getRendimento()
        );
    }

    public ContaPoupanca toEntity() {
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setTipo("poupanca");
        contaPoupanca.setNumero(numero);
        contaPoupanca.setSaldo(saldo);
        contaPoupanca.setRendimento(rendimento);
        return contaPoupanca;
    }
}
