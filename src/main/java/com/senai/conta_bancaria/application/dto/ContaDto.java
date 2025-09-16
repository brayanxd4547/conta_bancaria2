package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public interface ContaDto {
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

    public static ContaPoupancaDto fromEntity(ContaPoupanca contaPoupanca) {
        if (contaPoupanca == null) return null;
        return new ContaPoupancaDto(
                contaPoupanca.getId(),
                contaPoupanca.getNumero(),
                contaPoupanca.getSaldo(),
                contaPoupanca.getRendimento()
        );
    }

    public ContaCorrente toEntity(BigDecimal limite, BigDecimal taxa);

    public ContaPoupanca toEntity(BigDecimal rendimento);
}