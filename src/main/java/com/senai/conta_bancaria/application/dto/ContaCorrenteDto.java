package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.ContaCorrente;

public abstract record ContaCorrenteDto extends ContaDto(
        String id,
        Long numero,
        double saldo,
        double limite,
        double taxa
) {
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

    public ContaCorrente toEntity() {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setTipo("corrente");
        contaCorrente.setNumero(numero);
        contaCorrente.setSaldo(saldo);
        contaCorrente.setLimite(limite);
        contaCorrente.setTaxa(taxa);
        return contaCorrente;
    }
}
