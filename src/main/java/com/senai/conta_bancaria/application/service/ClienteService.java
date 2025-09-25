package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ClienteAtualizadoDto;
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

    // CREATE
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

    // READ
    @Transactional(readOnly = true)
    public List<ClienteResponseDto> listarTodosOsClientes() {
        return repository
                .findAllByAtivoTrue()
                .stream()
                .map(ClienteResponseDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDto buscarClientePorCpf(Long cpf) {
        return ClienteResponseDto.fromEntity(procurarClienteAtivoPorCpf(cpf));
    }

    // UPDATE
    public ClienteResponseDto atualizarClientePorCpf(Long cpf, ClienteAtualizadoDto dto) {
        Cliente cliente = procurarClienteAtivoPorCpf(cpf);

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDto.fromEntity(repository.save(cliente));
    }

    // DELETE
    public void apagarClientePorCpf(Long cpf) {
        Cliente cliente = procurarClienteAtivoPorCpf(cpf);

        cliente.setAtivo(false);
        cliente.getContas()
                .forEach(c -> c.setAtivo(false));

        repository.save(cliente);
    }

    // Método auxiliador para as requisições
    private Cliente procurarClienteAtivoPorCpf(Long cpf) {
        return repository
                .findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}