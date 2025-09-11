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
    public List<ContaDto> listarContas() {
        return contaRepository
                .findAll()
                .stream()
                .map(c -> (c.getTipo().equals("corrente") ? ContaCorrenteDto.fromEntity((ContaCorrente) c) : ContaPoupancaDto.fromEntity((ContaPoupanca) c)))
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaBancariaDto buscarContaBancariaPorId(String id) {
        return contaBancariaRepository
                .findById(id)
                .map(ContaBancariaDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("ContaBancaria não encontrado"));
    }

    public ContaBancariaDto salvarContaBancaria(ContaBancariaDto dto) {
        Conta salvo = contaBancariaRepository.save(dto.toEntity());
        return ContaBancariaDto.fromEntity(salvo);
    }

    public ContaBancariaDto atualizarContaBancaria(String id, ContaBancariaDto dto) {
        Conta antigoConta = contaBancariaRepository.findById(id).orElse(null);
        if (antigoConta == null) return null;

        antigoConta.setNome(dto.nome());
        antigoConta.setCpf(dto.cpf());
        antigoConta.setContas(dto.contas());

        Conta novoConta = contaBancariaRepository.save(antigoConta);
        return ContaBancariaDto.fromEntity(novoConta);
    }

    public void apagarContaBancaria(String id) {
        contaBancariaRepository.deleteById(id);
    }
}