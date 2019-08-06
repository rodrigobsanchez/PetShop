package br.com.tt.petshop.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "TB_PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column (name = "VALOR_PRODUTO")
    private BigDecimal valor;

    @Column (name = "DESCRICAO_PRODUTO")
    private String descricao;

    @ManyToOne
    @JoinColumn (name = "ID_ANIMAL")
    private Animal animal;

    public Produto() {
    }

    public Produto(BigDecimal valor, String descricao, String animalNome) {
        this.valor = valor;
        this.descricao = descricao;

    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}
