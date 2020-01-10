package br.com.tt.petshop.model;

import br.com.tt.petshop.model.vo.Cpf;
import org.hibernate.validator.constraints.br.CPF;

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

    //**************
    @Embedded
    private Cpf cpf;

    @Column(name = "INADIMPLENTE")
    private Boolean inadimplente;

    // mappedBy - indica qual campo será a referencia na Tabela Cliente
    @OneToMany(mappedBy = "cliente")
    private List<Animal> meusAnimais;

    @ManyToOne
    @JoinColumn(name = "ID_UNIDADE")
    private Unidade unidade;

/*
 fetch = FetchType.LAZY ou .EAGER
   fetch = como ele deve pegar a entidade cliente... perigoso a utilizacao...
      FetchType LAZY = utilizado na listas... quando se utiliza o get de 'meus animais' ele utlizará o minimo de selects necessarios para
      pegar os dados das tabelas...
      Fetch EAGER ira usar fetch (usará todos os selects que tem....)
 */
/*
, cascade = {CascadeType.REMOVE, CascadeType.MERGE}
  cascade = quando se quiser salvar o cliente...
  .MERGE = save update..
  .ALL - todas as operacoes
  .DETACH parte do ciclo de vida de uma entidade.
 */



    public Cliente() {

        this.inadimplente = Boolean.FALSE;
        this.cpf = new Cpf(null);
    }

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(Long id, String nome, String cpf, Boolean b, Long unidadeId) {
        this.id = id;
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        //this.meusAnimais = new ArrayList<Animal>();
        this.inadimplente = Boolean.FALSE;
        this.unidade = new Unidade(null, null);
        unidade.setId(unidadeId);
    }

    public Cliente(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = new Cpf(cpf);
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



    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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


    public Boolean isInadimplente(){
        return Objects.nonNull(inadimplente) && inadimplente;
    }

    public Boolean getInadimplente() {
        return inadimplente;
    }

    public void setInadimplente(Boolean inadimplente) {
        this.inadimplente = inadimplente;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }



    public List<Animal> getMeusAnimais() {
        return meusAnimais;
    }

    public void setMeusAnimais(List<Animal> meusAnimais) {
        this.meusAnimais = meusAnimais;
    }




}

