package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteDto;
import com.senai.conta_bancaria.domain.entity.Cliente;
import com.senai.conta_bancaria.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ClienteDto> listarClientes() {
        return clienteRepository
                .findAll()
                .stream()
                .map(ClienteDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteDto buscarClientePorId(String id) {
        return clienteRepository
                .findById(id)
                .map(ClienteDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public ClienteDto salvarCliente(ClienteDto dto) {
        Cliente salvo = clienteRepository.save(dto.toEntity());
        return ClienteDto.fromEntity(salvo);
    }

    public ClienteDto atualizarCliente(String id, ClienteDto dto) {
        Cliente antigoCliente = clienteRepository.findById(id).orElse(null);
        if (antigoCliente == null) return null;

        antigoCliente.setNome(dto.nome());
        antigoCliente.setCpf(dto.cpf());
        antigoCliente.setContas(dto.contas());

        Cliente novoCliente = clienteRepository.save(antigoCliente);
        return ClienteDto.fromEntity(novoCliente);
    }

    public void apagarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}