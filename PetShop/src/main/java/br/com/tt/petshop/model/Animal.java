package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.vo.DataNascimento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_animal")
public class Animal {
    @Id
    //gerar valor autormatico para Id primary (PRIMARY_KEY do SQL)do animal.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // nomear a header de 'id'
    @Column(name = "ID_ANIMAL")
    private Long id;

    @NotBlank
    private String nome;


    @Embedded
    private DataNascimento dataNascimento;

    //make the relation to be an String
    @Enumerated(EnumType.STRING)
    @NotNull
    private EspecieEnum especie;
//    private List<Vacina> vacinas;
//    private List<Procedimento> procedimentos;

//    @Column (name = "client_id", updatable = false, insertable = false)
//    private Long clientId;
    /*
    Esse é um meio de usar as duas variávveis....
     */

    //Essas duas annotattions sempre utilizadas juntas...no caso a lista de animais possui um client ID
    @ManyToOne
    @JoinColumn (name = "ID_CLIENTE")
    //Json ignore é necessario pois o cliente possui uma lista de animais...e animal ´possui cliente.
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_UNIDADE")
    private Unidade unidade;

    @OneToMany(mappedBy = "animal")
    private List<Produto> produtos;

    // tem que ter os dois construtories esse o default e o outro... logo abaixo
    public Animal(){
        this.dataNascimento = new DataNascimento();
    }


    public Animal(String nome, LocalDate dataNascimento, EspecieEnum especie, Long clientId) {
        this.nome = nome;
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.especie = especie;
        this.cliente = new Cliente(clientId, null , null);
//        this.unidade = new Unidade(null, null);
//        unidade.setId(1L);
    }



    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

}
