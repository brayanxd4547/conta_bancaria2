package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ClienteAtualizadoDto;
import com.senai.conta_bancaria.application.dto.ClienteRegistroDto;
import com.senai.conta_bancaria.application.dto.ClienteResponseDto;
import com.senai.conta_bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    // CREATE
    @PostMapping
    public ResponseEntity<ClienteResponseDto> registrarCliente(@RequestBody ClienteRegistroDto dto) {
        return ResponseEntity // retorna o código de status
                .created(URI.create("api/cliente")) // status code: 201 (criado com êxito)
                .body(service.registrarCliente(dto));
    }

    // READ
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarTodosOsClientes() {
        return ResponseEntity
                .ok(service.listarTodosOsClientes()); // status code: 200 (encontrado com êxito)
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDto> buscarClientePorCpf(@PathVariable Long cpf) {
        return ResponseEntity
                .ok(service.buscarClientePorCpf(cpf));
    }

    // UPDATE
    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDto> atualizarClientePorCpf(@PathVariable Long cpf, @RequestBody ClienteAtualizadoDto dto) {
        return ResponseEntity
                .ok(service.atualizarClientePorCpf(cpf, dto));
    }

    // DELETE
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> apagarClientePorCpf(@PathVariable Long cpf) {
        service.apagarClientePorCpf(cpf);
        return ResponseEntity
                .noContent() // status code: 204 (encontrado sem conteúdo)
                .build();
    }
}