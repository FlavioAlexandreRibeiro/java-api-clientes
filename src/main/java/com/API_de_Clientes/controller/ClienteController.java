package com.API_de_Clientes.controller;

import com.API_de_Clientes.dto.ClienteRequestDTO;
import com.API_de_Clientes.dto.ClienteResponseDTO;
import com.API_de_Clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(
            @Valid @RequestBody ClienteRequestDTO cliente) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.salvar(cliente));
    }


    @GetMapping
    public List<ClienteResponseDTO> listar() {
        return clienteService.listar();
    }


    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO cliente) {

        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
