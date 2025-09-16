package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ClienteDto;
import com.senai.conta_bancaria.application.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDto> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteDto buscarClientePorId(@PathVariable String id) {
        return clienteService.buscarClientePorId(id);
    }

    @PostMapping
    public ClienteDto salvarCliente(@RequestBody ClienteDto dto) {
        return clienteService.salvarCliente(dto);
    }

    @PutMapping("/{id}")
    public ClienteDto atualizarCliente(@PathVariable String id, @RequestBody ClienteDto dto) {
        return clienteService.atualizarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void apagarCliente(@PathVariable String id) {
        clienteService.apagarCliente(id);
    }
}