package com.senai.conta_bancaria.application.dto;

import java.math.BigDecimal;

public record ContaAtualizacaoDto(
        BigDecimal saldo,
        BigDecimal limite,
        BigDecimal taxa,
        BigDecimal rendimento
) {
}
