package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ContaDto;
import com.senai.conta_bancaria.application.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<ContaDto> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public ContaDto buscarContaPorId(@PathVariable String id) {
        return contaService.buscarContaPorId(id);
    }

    @PostMapping
    public ContaDto salvarConta(@RequestBody ContaDto dto) {
        return contaService.salvarConta(dto);
    }

    @PutMapping("/{id}")
    public ContaDto atualizarConta(@PathVariable String id, @RequestBody ContaDto dto) {
        return contaService.atualizarConta(id, dto);
    }

    @DeleteMapping("/{id}")
    public void apagarConta(@PathVariable String id) {
        contaService.apagarConta(id);
    }
}