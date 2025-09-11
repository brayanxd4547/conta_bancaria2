package com.senai.conta_bancaria.domain.entity;

import com.senai.conta_bancaria.application.dto.ContaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String tipo;
    private Long numero;
    private double saldo;

    public abstract ContaDto toDto();
}
