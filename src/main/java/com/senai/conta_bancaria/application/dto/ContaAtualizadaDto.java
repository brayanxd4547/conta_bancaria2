package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;

import java.math.BigDecimal;

public record ContaAtualizadaDto(
        Long numero,
        BigDecimal saldo,
        Cliente cliente
) {
}
