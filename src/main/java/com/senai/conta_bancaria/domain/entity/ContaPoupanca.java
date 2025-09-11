package com.senai.conta_bancaria.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContaPoupanca extends Conta {
    private double rendimento;
}
