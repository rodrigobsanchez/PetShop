package br.com.tt.petshop.dto.builder;

import br.com.tt.petshop.dto.ClienteDto;

public class ClienteDtoBuilder {

    private Long id;
    private String nome;
    private String cpf;

    private ClienteDto dto = new ClienteDto();

    public ClienteDtoBuilder() {
    }

    public ClienteDtoBuilder(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClienteDto setId(Long id){
        dto.setId(id);
        return dto;
    }
    public ClienteDto setNome(String nome){
        dto.setNome(nome);
        return dto;
    }
}
