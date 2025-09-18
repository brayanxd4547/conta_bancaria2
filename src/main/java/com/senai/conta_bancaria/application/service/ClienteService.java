package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteRegistroDto;
import com.senai.conta_bancaria.application.dto.ClienteResponseDto;
import com.senai.conta_bancaria.application.dto.ContaResumoDto;
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
        var cliente = repository
                .findByCpfAndStatusTrue(dto.cpf())
                .orElseGet(
                        () -> repository.save(dto.toEntity())
                );
        var contas = cliente.getContas();
        var novaConta = dto.contaDto().toEntity(cliente);

        boolean temTipo = contas
                .stream()
                .anyMatch(c -> c.getTipoConta().equals(dto.conta().tipoConta()));
    }

    @Transactional(readOnly = true)
    public List<ClienteRegistroDto> listarClientes() {
        return repository
                .findAll()
                .stream()
                .map(ClienteRegistroDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteRegistroDto buscarClientePorId(String id) {
        return repository
                .findById(id)
                .map(ClienteRegistroDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }



    public ClienteRegistroDto atualizarCliente(String id, ClienteRegistroDto dto) {
        Cliente antigoCliente = repository.findById(id).orElse(null);
        if (antigoCliente == null) return null;

        antigoCliente.setNome(dto.nome());
        antigoCliente.setCpf(dto.cpf());
        antigoCliente.setContas(dto.contas());

        Cliente novoCliente = repository.save(antigoCliente);
        return ClienteRegistroDto.fromEntity(novoCliente);
    }

    public void apagarCliente(String id) {
        repository.deleteById(id);
    }
}