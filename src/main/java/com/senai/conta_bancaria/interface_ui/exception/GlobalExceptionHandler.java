package com.senai.conta_bancaria.interface_ui.exception;

import com.senai.conta_bancaria.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContaDeMesmoTipoException.class)
    public ResponseEntity<String> handleContaDeMesmoTipoException(ContaDeMesmoTipoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<String> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RendimentoInvalidoException.class)
    public ResponseEntity<String> handleRendimentoInvalidoException(RendimentoInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TipoDeContaInvalidaException.class)
    public ResponseEntity<String> handleTipoDeContaInvalidaException(TipoDeContaInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferenciaParaMesmaContaException.class)
    public ResponseEntity<String> handleTransferenciaParaMesmaContaException(TransferenciaParaMesmaContaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValorNegativoException.class)
    public ResponseEntity<String> handleValorNegativoException(ValorNegativoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}