package com.API_de_Clientes.dto;

import com.API_de_Clientes.entity.Cliente;

public record ClienteResponseDTO(

        Long id,
        String nome,
        String email,
        String telefone
) {

    public static ClienteResponseDTO mapearParaDto(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}




