package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDto;
import com.senai.conta_bancaria.application.dto.ClienteResponseDto;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponseDto registrarCliente(ClienteRegistroDto dto) {
        Cliente clienteRegistrado = repository // verifica se o cliente já existe
                .findByCpfAndAtivoTrue(dto.cpf())
                .orElseGet( // se não existir, cria um novo
                        () -> repository.save(dto.toEntity())
                );
        List<Conta> contas = clienteRegistrado.getContas();
        Conta novaConta = dto.conta().toEntity(clienteRegistrado);

        boolean temMesmoTipo = contas // verifica se o cliente já tem uma conta do mesmo tipo
                .stream()
                .anyMatch(c -> c.getClass().equals(novaConta.getClass()) && c.isAtivo());

        if (temMesmoTipo)
            throw new RuntimeException("Cliente já possui uma conta do mesmo tipo");

        clienteRegistrado.getContas().add(novaConta);

        return ClienteResponseDto.fromEntity(repository.save(clienteRegistrado));
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDto> listarClientesAtivos() {
        return repository
                .findAllByAtivoTrue()
                .stream()
                .map(ClienteResponseDto::fromEntity)
                .toList();
    }

   @Transactional(readOnly = true)
    public ClienteResponseDto buscarClientePorId(String id) {
        return repository
                .findById(id)
                .map(ClienteResponseDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional(readOnly = true)
    public ClienteResponseDto buscarClienteAtivoPorCpf(Long cpf) {
        return repository
                .findByCpfAndAtivoTrue(cpf)
                .map(ClienteResponseDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    /*public ClienteRegistroDto atualizarCliente(String id, ClienteAtualizacaoDto dto) {
        Cliente antigoCliente = repository.findById(id).orElse(null);
        if (antigoCliente == null) return null;

        ContaResumoDto aa = new ContaResumoDto();

        antigoCliente.setNome(dto.nome());
        antigoCliente.setCpf(dto.cpf());
        antigoCliente.setContas(dto.contas()
                .stream()
                .map(c -> ContaResumoDto.toEntity(c)));

        Cliente novoCliente = repository.save(antigoCliente);
        return ClienteRegistroDto.fromEntity(novoCliente);
    }*/
/*
    public void apagarCliente(String id) {
        repository.deleteById(id);
    }*/
}