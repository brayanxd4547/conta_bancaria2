package com.senai.conta_bancaria.application.dto;

import java.util.List;

public record ClienteResponseDto (
        String id,
        String nome,
        Long cpf,
        List<ContaResumoDto> contas
){
}
