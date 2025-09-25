package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaAtualizadaDto;
import com.senai.conta_bancaria.application.dto.ContaResumoDto;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContaService {
    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    // CREATE: embutido em Cliente

    // READ
    @Transactional(readOnly = true)
    public List<ContaResumoDto> listarTodasAsContas() {
        return repository
                .findAllByAtivoTrue()
                .stream()
                .map(ContaResumoDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ContaResumoDto> listarContasPorCpf(Long cpf) {
        return repository
                .findAllByAtivoTrue()
                .stream()
                .filter(c -> c.getCliente().getCpf().equals(cpf))
                .map(ContaResumoDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDto buscarContaPorNumero(Long cpf) {
        return ContaResumoDto.fromEntity(procurarContaAtivaPorNumero(cpf));
    }

    // UPDATE
    public ContaResumoDto atualizarContaPorNumero(Long numero, ContaAtualizadaDto dto) {
        Conta conta = procurarContaAtivaPorNumero(numero);

        conta.setNumero(dto.numero());
        conta.setSaldo(dto.saldo());
        conta.setCliente(dto.cliente());

        return ContaResumoDto.fromEntity(repository.save(conta));
    }

    // DELETE
    public void apagarContaPorNumero(Long numero) {
        Conta conta = procurarContaAtivaPorNumero(numero);

        conta.setAtivo(false);

        repository.save(conta);
    }

    // Método auxiliador para as requisições
    private Conta procurarContaAtivaPorNumero(Long numero) {
        return repository
                .findByNumeroAndAtivoTrue(numero)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}