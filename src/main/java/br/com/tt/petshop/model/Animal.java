package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class Animal {

    private String nome;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
    private EspecieEnum especie;
    private List<Vacina> vacinas;
    private List<Procedimento> procedimentos;
    private Long clientId;

    // tem que ter os dois construtories esse o default e o outro... logo abaixo
    public Animal(){

    }


    public Animal(String nome, LocalDate dataNascimento, EspecieEnum especie, Long clientId) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.clientId = clientId;
    }

    public EspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEnum especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public Long getClientId() {

        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }



}
