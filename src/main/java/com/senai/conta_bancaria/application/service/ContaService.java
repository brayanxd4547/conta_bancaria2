package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaAtualizacaoDto;
import com.senai.conta_bancaria.application.dto.ContaResumoDto;
import com.senai.conta_bancaria.application.dto.TransferenciaDto;
import com.senai.conta_bancaria.application.dto.ValorSaqueDepositoDto;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.ContaCorrente;
import com.senai.conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public ContaResumoDto buscarConta(Long cpf) {
        return ContaResumoDto.fromEntity(procurarContaAtiva(cpf));
    }

    // UPDATE
    public ContaResumoDto atualizarConta(Long numero, ContaAtualizacaoDto dto) {
        Conta conta = procurarContaAtiva(numero);

        conta.setSaldo(dto.saldo());
        if (conta instanceof ContaCorrente contaCorrente) {
            contaCorrente.setLimite(dto.limite());
            contaCorrente.setTaxa(dto.taxa());
        } else if (conta instanceof ContaPoupanca contaPoupanca) {
            contaPoupanca.setRendimento(dto.rendimento());
        } else {
            throw new RuntimeException("Tipo de conta inválida.");
        }

        return ContaResumoDto.fromEntity(repository.save(conta));
    }

    // DELETE
    public void apagarConta(Long numero) {
        Conta conta = procurarContaAtiva(numero);

        conta.setAtivo(false);

        repository.save(conta);
    }

    // Ações específicas

    // Saque e depósito
    public ContaResumoDto sacar(Long numero, ValorSaqueDepositoDto dto) {
        Conta conta = procurarContaAtiva(numero);

        conta.sacar(dto.valor());

        return ContaResumoDto.fromEntity(repository.save(conta));
    }

    public ContaResumoDto depositar(Long numero, ValorSaqueDepositoDto dto) {
        Conta conta = procurarContaAtiva(numero);

        conta.depositar(dto.valor());

        return ContaResumoDto.fromEntity(repository.save(conta));
    }

    // Transferência entre contas
    public ContaResumoDto transferir(Long numeroOrigem, TransferenciaDto dto) {
        Conta contaOrigem = procurarContaAtiva(numeroOrigem);
        Conta contaDestino = procurarContaAtiva(dto.numeroDestino());

        contaOrigem.sacar(dto.valor());
        contaDestino.depositar(dto.valor());

        repository.save(contaOrigem);
        return ContaResumoDto.fromEntity(repository.save(contaDestino));
    }

    // Mét0do auxiliar para as requisições
    private Conta procurarContaAtiva(Long numero) {
        return repository
                .findByNumeroAndAtivoTrue(numero)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}