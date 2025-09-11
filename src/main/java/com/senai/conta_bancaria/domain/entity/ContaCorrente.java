package com.senai.conta_bancaria.domain.entity;

import com.senai.conta_bancaria.application.dto.ContaCorrenteDto;
import com.senai.conta_bancaria.application.dto.ContaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContaCorrente extends Conta {
    private double limite;
    private double taxa;

    @Override
    public ContaDto toDto() {
        return ContaCorrenteDto.fromEntity(this);
    }
}
