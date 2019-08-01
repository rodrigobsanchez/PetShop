package br.com.tt.petshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name ="NOME_CLIENTE")
    private String nome;
    @Column(name = "CPF_CLIENTE")
    private String cpf;
    @Column(name = "INADIMPLENTE")
    private Boolean inadimplente;
//    private List<Animal> meusAnimais;




    public Cliente() {
        this.inadimplente = Boolean.FALSE;
    }

    public Cliente(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        //this.meusAnimais = new ArrayList<Animal>();
        this.inadimplente = Boolean.FALSE;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /*
    Os metodos equal e hashCode permitem que a Id dos clientes sejam comparadas e verificar se sao o mesmo objeto.
     */




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

    public Boolean isInadimplente(){
        return Objects.nonNull(inadimplente) && inadimplente;
    }

    public Boolean getInadimplente() {
        return inadimplente;
    }

    public void setInadimplente(Boolean inadimplente) {
        this.inadimplente = inadimplente;
    }
//
//    public List<Animal> getMeusAnimais() {
//        return meusAnimais;
//    }
//
//    public void setMeusAnimais(List<Animal> meusAnimais) {
//        this.meusAnimais = meusAnimais;
//    }




}

