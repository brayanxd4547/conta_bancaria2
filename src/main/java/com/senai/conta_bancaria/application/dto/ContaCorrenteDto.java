package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaCorrenteDto(
        String id,
        Long numero,
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal taxa
) implements ContaDto {
    public static ContaCorrenteDto fromEntity(ContaCorrente contaCorrente) {
        if (contaCorrente == null) return null;
        return new ContaCorrenteDto(
                contaCorrente.getId(),
                contaCorrente.getNumero(),
                contaCorrente.getSaldo(),
                contaCorrente.getLimite(),
                contaCorrente.getTaxa()
        );
    }

    @Override
    public ContaCorrente toEntity(BigDecimal limite, BigDecimal taxa) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setNumero(numero);
        contaCorrente.setSaldo(saldo);
        contaCorrente.setLimite(limite);
        contaCorrente.setTaxa(taxa);
        return contaCorrente;
    }

    @Override
    public ContaPoupanca toEntity(BigDecimal rendimento) {
        return null;
    }
}