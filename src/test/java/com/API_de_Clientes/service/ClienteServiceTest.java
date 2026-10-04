package com.API_de_Clientes.service;

import com.API_de_Clientes.dto.ClienteRequestDTO;
import com.API_de_Clientes.dto.ClienteResponseDTO;
import com.API_de_Clientes.entity.Cliente;
import com.API_de_Clientes.exception.EmailJaCadastradoException;
import com.API_de_Clientes.exception.ResourceNotFoundException;
import com.API_de_Clientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteRequestDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setEmail("joao@example.com");
        cliente.setTelefone("11999999999");

        clienteDTO = new ClienteRequestDTO();
        clienteDTO.setNome("João Silva");
        clienteDTO.setEmail("joao@example.com");
        clienteDTO.setTelefone("11999999999");
    }

    @Test
    void salvar_DeveRetornarClienteResponseDTO_QuandoEmailNaoExiste() {
        when(clienteRepository.existsByEmail(clienteDTO.getEmail())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDTO resultado = clienteService.salvar(clienteDTO);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.nome());
        assertEquals(cliente.getEmail(), resultado.email());
        assertEquals(cliente.getTelefone(), resultado.telefone());
        verify(clienteRepository).existsByEmail(clienteDTO.getEmail());
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void salvar_DeveLancarExcecao_QuandoEmailJaExiste() {
        when(clienteRepository.existsByEmail(clienteDTO.getEmail())).thenReturn(true);

        assertThrows(EmailJaCadastradoException.class, () -> clienteService.salvar(clienteDTO));
        verify(clienteRepository).existsByEmail(clienteDTO.getEmail());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void buscarPorId_DeveRetornarClienteResponseDTO_QuandoClienteExiste() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteResponseDTO resultado = clienteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.nome());
        verify(clienteRepository).findById(1L);
    }

    @Test
    void buscarPorId_DeveLancarExcecao_QuandoClienteNaoExiste() {
        when(clienteRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clienteService.buscarPorId(999L));
        verify(clienteRepository).findById(999L);
    }

    @Test
    void listar_DeveRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteResponseDTO> resultado = clienteService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(cliente.getNome(), resultado.get(0).nome());
        verify(clienteRepository).findAll();
    }

    @Test
    void atualizar_DeveRetornarClienteAtualizado_QuandoClienteExiste() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDTO resultado = clienteService.atualizar(1L, clienteDTO);

        assertNotNull(resultado);
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoClienteNaoExiste() {
        when(clienteRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clienteService.atualizar(999L, clienteDTO));
        verify(clienteRepository).findById(999L);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void atualizar_DeveLancarExcecao_QuandoNovoEmailJaExiste() {
        ClienteRequestDTO novoDTO = new ClienteRequestDTO();
        novoDTO.setNome("João Silva");
        novoDTO.setEmail("outro@example.com");
        novoDTO.setTelefone("11999999999");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.existsByEmail(novoDTO.getEmail())).thenReturn(true);

        assertThrows(EmailJaCadastradoException.class, () -> clienteService.atualizar(1L, novoDTO));
        verify(clienteRepository).findById(1L);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void deletar_DeveDeletarCliente_QuandoClienteExiste() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deletar(1L);

        verify(clienteRepository).existsById(1L);
        verify(clienteRepository).deleteById(1L);
    }

    @Test
    void deletar_DeveLancarExcecao_QuandoClienteNaoExiste() {
        when(clienteRepository.existsById(999L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> clienteService.deletar(999L));
        verify(clienteRepository).existsById(999L);
        verify(clienteRepository, never()).deleteById(anyLong());
    }
}
