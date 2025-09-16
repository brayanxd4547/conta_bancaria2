package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaCorrenteDto;
import com.senai.conta_bancaria.application.dto.ContaDto;
import com.senai.conta_bancaria.application.dto.ContaPoupancaDto;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Transactional(readOnly = true)
    public List<Record> listarContas() {
        return contaRepository
                .findAll()
                .stream()
                .map(c -> {
                    if (c instanceof ContaCorrente)
                        return ContaCorrenteDto.fromEntity(c);
                    else
                        return ContaPoupancaDto.fromEntity(c);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ContaCorrenteDto> listarContasCorrente() {
        return contaRepository
                .findAll()
                .stream()
                .filter(c -> c instanceof ContaCorrente)
                .map(c -> ContaCorrenteDto.fromEntity((ContaCorrente) c))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ContaPoupancaDto> listarContasPoupanca() {
        return contaRepository
                .findAll()
                .stream()
                .filter(c -> c instanceof ContaPoupanca)
                .map(c -> ContaPoupancaDto.fromEntity((ContaPoupanca) c))
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaDto buscarContaPorId(String id) {
        return contaRepository
                .findById(id)
                .map(ContaDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Conta não encontrado"));
    }

    public ContaDto salvarConta(ContaDto dto) {
        Conta salvo = contaRepository.save(dto.toEntity());
        return ContaDto.fromEntity(salvo);
    }

    public ContaDto atualizarConta(String id, ContaDto dto) {
        Conta antigoConta = contaRepository.findById(id).orElse(null);
        if (antigoConta == null) return null;

        antigoConta.setNome(dto.nome());
        antigoConta.setCpf(dto.cpf());
        antigoConta.setContas(dto.contas());

        Conta novoConta = contaRepository.save(antigoConta);
        return ContaDto.fromEntity(novoConta);
    }

    public void apagarConta(String id) {
        contaRepository.deleteById(id);
    }
}