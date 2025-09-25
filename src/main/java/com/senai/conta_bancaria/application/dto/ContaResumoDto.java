package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaResumoDto(
        Long numero,
        String tipo,
        BigDecimal saldo
) {
    public static ContaResumoDto fromEntity(Conta conta) {
        return new ContaResumoDto(
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo()
        );
    }
    public Conta toEntity(Cliente cliente){
        return switch (tipo) {
            case "CORRENTE" -> ContaCorrente.builder()
                    .id(null)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativo(true)
                    .cliente(cliente)
                    .build();
            case "POUPANCA" -> ContaPoupanca.builder()
                    .id(null)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativo(true)
                    .cliente(cliente)
                    .build();
            default -> throw new IllegalArgumentException("Tipo de conta inválida: " + tipo);
        };
    }
}
