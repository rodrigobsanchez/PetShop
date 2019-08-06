package br.com.tt.petshop.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_UNIDADE")
public class Unidade {

    @Id
    //gerar valor autormatico para Id primary (PRIMARY_KEY do SQL)do animal.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // nomear a header de 'id'
    @Column(name = "ID_UNIDADE")
    private Long id;

    @Column(name = "NOME_UNIDADE")
    private String nome;

    @Column(name = "ENDERECO_UNIDADE")
    private String endereco;
//
//
//    private List<Cliente> listaClientes;
//
//

    public Unidade() {
    }

    public Unidade(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
//        listaClientes = new ArrayList<Cliente>();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

//    public List<Cliente> getListaClientes() {
//        return listaClientes;
//    }
//
//    public void setListaClientes(List<Cliente> listaClientes) {
//        this.listaClientes = listaClientes;
//    }


}
