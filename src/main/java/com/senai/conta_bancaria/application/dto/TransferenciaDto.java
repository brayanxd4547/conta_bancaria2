package com.senai.conta_bancaria.application.dto;

import java.math.BigDecimal;

public record TransferenciaDto(
        Long numeroDestino,
        BigDecimal valor
) {
}