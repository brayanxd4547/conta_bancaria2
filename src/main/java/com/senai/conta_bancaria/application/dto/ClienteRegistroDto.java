package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Cliente;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.ArrayList;

@Builder
public record ClienteRegistroDto(
        @NotBlank(message = "O nome não pode ser vazio.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @Positive(message = "O CPF não pode ser negativo.")
        @Max(value = 99999999999L, message = "O CPF deve ter até 11 digitos.")
        Long cpf,

        ContaResumoDto conta
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(nome)
                .cpf(cpf)
                .contas(new ArrayList<>())
                .build();
    }
}