package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ContaAtualizadaDto;
import com.senai.conta_bancaria.application.dto.ContaResumoDto;
import com.senai.conta_bancaria.application.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService service;

    // CREATE: embutido em Cliente

    // READ
    @GetMapping
    public ResponseEntity<List<ContaResumoDto>> listarTodasAsContas() {
        return ResponseEntity
                .ok(service.listarTodasAsContas()); // status code: 200 (encontrado com êxito)
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<ContaResumoDto>> listarContasPorCpf(@PathVariable Long cpf) {
        return ResponseEntity
                .ok(service.listarContasPorCpf(cpf)); // status code: 200 (encontrado com êxito)
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ContaResumoDto> buscarContaPorNumero(@PathVariable Long numero) {
        return ResponseEntity
                .ok(service.buscarContaPorNumero(numero));
    }

    // UPDATE
    @PutMapping("/{numero}")
    public ResponseEntity<ContaResumoDto> atualizarContaPorNumero(@PathVariable Long numero, @RequestBody ContaAtualizadaDto dto) {
        return ResponseEntity
                .ok(service.atualizarContaPorNumero(numero, dto));
    }

    // DELETE
    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> apagarContaPorNumero(@PathVariable Long numero) {
        service.apagarContaPorNumero(numero);
        return ResponseEntity
                .noContent()
                .build();
    }
}