package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.dto.ContaDto;
import com.senai.conta_bancaria.application.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contaBancaria")
public class ContaBancariaController {
    private final ContaService contaService;
    public ContaBancariaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<ContaDto> listarContas() {
        return contaService.listarContas();
    }
}









    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoDTO> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    public AlunoDTO buscarAlunoPorId(@PathVariable String id) {
        return alunoService.buscarAlunoPorId(id);
    }

    @PostMapping
    public AlunoDTO salvarAluno(@RequestBody AlunoDTO dto) {
        return alunoService.salvarAluno(dto);
    }

    @PutMapping("/{id}")
    public AlunoDTO atualizarAluno(@PathVariable String id, @RequestBody AlunoDTO dto) {
        return alunoService.atualizarAluno(id, dto);
    }

    @DeleteMapping("/{id}")
    public void apagarAluno(@PathVariable String id) {
        alunoService.apagarAluno(id);
    }