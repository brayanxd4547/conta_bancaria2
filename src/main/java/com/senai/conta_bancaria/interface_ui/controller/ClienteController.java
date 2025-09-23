package com.senai.conta_bancaria.interface_ui.controller;

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

    @PostMapping
    public ResponseEntity<ClienteResponseDto> registrarCliente(@RequestBody ClienteRegistroDto dto) {
        return ResponseEntity // retorna o código de status
                .created(URI.create("api/cliente")) // status code: 201 (criado com êxito)
                .body(service.registrarCliente(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarClientesAtivos() {
        return ResponseEntity
                .ok(service.listarClientesAtivos()); // status code: 200 (encontrado com êxito)
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> buscarClientePorId(@PathVariable String id) {
        return ResponseEntity
                .ok(service.buscarClientePorId(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDto> buscarClienteAtivoPorCpf(@PathVariable Long cpf) {
        return ResponseEntity
                .ok(service.buscarClienteAtivoPorCpf(cpf));
    }

    /*@PutMapping("/{id}")
    public ClienteRegistroDto atualizarCliente(@PathVariable String id, @RequestBody ClienteRegistroDto dto) {
        return ResponseEntity
                .created(URI.create("api/cliente"))
                .body(service.atualizarCliente(id, dto));
    }*/
/*
    @DeleteMapping("/{id}")
    public void apagarCliente(@PathVariable String id) {
        service.apagarCliente(id);
    }*/
}