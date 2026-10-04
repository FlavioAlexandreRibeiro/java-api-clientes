package com.API_de_Clientes.service;

import com.API_de_Clientes.dto.ClienteRequestDTO;
import com.API_de_Clientes.dto.ClienteResponseDTO;
import com.API_de_Clientes.entity.Cliente;
import com.API_de_Clientes.exception.ClienteNaoEncontradoException;
import com.API_de_Clientes.exception.EmailJaCadastradoException;
import com.API_de_Clientes.exception.ResourceNotFoundException;
import com.API_de_Clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public ClienteResponseDTO salvar(ClienteRequestDTO clienteDTO) {

        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new EmailJaCadastradoException("Já existe um cliente com esse e-mail.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());

        Cliente salvo = clienteRepository.save(cliente);

        return ClienteResponseDTO.mapearParaDto(salvo);
    }


    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));

        return ClienteResponseDTO.mapearParaDto(cliente);
    }


    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteResponseDTO::mapearParaDto)
                .toList();
    }


    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDTO) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));

        if (!cliente.getEmail().equals(clienteDTO.getEmail())
                && clienteRepository.existsByEmail(clienteDTO.getEmail())) {

            throw new EmailJaCadastradoException(clienteDTO.getEmail());
        }

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return ClienteResponseDTO.mapearParaDto(clienteAtualizado);
    }


    public void deletar(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }
}
