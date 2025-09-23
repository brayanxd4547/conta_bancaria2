//package com.senai.conta_bancaria.interface_ui.controller;
//
//import com.senai.conta_bancaria.application.service.ContaService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/conta")
//public class  ContaController {
//    private final ContaService contaService;
//
//    public ContaController(ContaService contaService) {
//        this.contaService = contaService;
//    }
//
//    @GetMapping
//    public List<ContaRegistroDto> listarContas() {
//        return contaService.listarContas();
//    }
//
//    @GetMapping("/{id}")
//    public ContaRegistroDto buscarContaPorId(@PathVariable String id) {
//        return contaService.buscarContaPorId(id);
//    }
//
//    @PostMapping
//    public ContaRegistroDto salvarConta(@RequestBody ContaRegistroDto dto) {
//        return contaService.salvarConta(dto);
//    }
//
//    @PutMapping("/{id}")
//    public ContaRegistroDto atualizarConta(@PathVariable String id, @RequestBody ContaRegistroDto dto) {
//        return contaService.atualizarConta(id, dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void apagarConta(@PathVariable String id) {
//        contaService.apagarConta(id);
//    }
//}