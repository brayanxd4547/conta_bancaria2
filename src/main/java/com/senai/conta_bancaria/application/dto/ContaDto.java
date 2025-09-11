package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;

public record ContaDto(
        String id,
        Long numero,
        double saldo,
        double limite,
        double taxa,
        double rendimento
) {

    public ContaCorrenteDto fromEntity(Conta conta) {
        if (conta == null) return null;
        else if (conta.getTipo().equals("corrente")) return (Conta) ContaCorrenteDto.fromEntity((ContaCorrente) conta);
    }

    public Conta toEntity(String tipo) {
        Conta conta;

        if (tipo.equals("corrente")) {
            ContaCorrente contaCorrente = new ContaCorrente();

            contaCorrente.setLimite(limite);
            contaCorrente.setTaxa(taxa);

            conta = contaCorrente;
        } else {
            ContaPoupanca contaPoupanca = new ContaPoupanca();

            contaPoupanca.setRendimento(rendimento);

            conta = new ContaPoupanca();
        }

        conta.setNumero(numero);
        conta.setSaldo(saldo);

        return conta;
    }
}
