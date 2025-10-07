package com.senai.conta_bancaria.domain.exception;

public class ValorNegativoException extends RuntimeException {
    public ValorNegativoException(String operacao) {
        super("O valor de " + operacao + " deve ser maior que zero.");
    }
}