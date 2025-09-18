package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDto;
import com.senai.conta_bancaria.application.dto.ClienteResponseDto;
import com.senai.conta_bancaria.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ClienteResponseDto registrarCliente(@RequestBody ClienteRegistroDto dto) {
        return service.registrarCliente(dto);
    }

    @GetMapping
    public List<ClienteRegistroDto> listarClientes() {
        return service.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteRegistroDto buscarClientePorId(@PathVariable String id) {
        return service.buscarClientePorId(id);
    }



    @PutMapping("/{id}")
    public ClienteRegistroDto atualizarCliente(@PathVariable String id, @RequestBody ClienteRegistroDto dto) {
        return service.atualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void apagarCliente(@PathVariable String id) {
        service.apagarCliente(id);
    }
}