package br.com.tt.petshop.dto;

import javax.validation.constraints.*;
import java.util.List;


public class ClienteDto {

    /*
    Annotations da javax.validation...
     */
    //@NotNull
    //@NotEmpty
    private Long id;
    @NotBlank(message = "O nome deve ser informado!")
    @Size(min =2, max = 100, message = "Nome deve conter de 2 a 100 caracteres")
    private String nome;
    @Pattern(regexp = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}", message = "Cpf inválido") // regex do CPF...para applications
    private String cpf;
    // evitar colocar entidades dentros dos 'DTO's' se criar um Cliente dto é obrigatorio criar um dto para o Animal
    private List<AnimalDto> animais;

    public static ClienteDto builder(Long id, String nome){
       ClienteDto dto = new ClienteDto();
       dto.setId(id);
       dto.setNome(nome);
       return dto;
    }

    public ClienteDto(Long id, String nome, String cpf, List<AnimalDto> animais) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.animais = animais;
    }

    public ClienteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<AnimalDto> getAnimais() {
        return animais;
    }

    public void setAnimais(List<AnimalDto> animais) {
        this.animais = animais;
    }
}
