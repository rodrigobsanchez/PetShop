package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.vo.DataNascimento;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
// nomear a tabela --> @Table(name = "Tabela_Animal")
@Table(name = "tb_animal")
public class Animal {
    @Id
    //gerar valor autormatico para Id primary (PRIMARY_KEY do SQL)do animal.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // nomear a header de 'id'
    @Column(name = "codigo")
    private Long id;
    private String nome;
    @Embedded
    private DataNascimento dataNascimento;

    //make the relation to be an String
    @Enumerated(EnumType.STRING)
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
    @JoinColumn (name = "CLIENT_ID")
    private Cliente cliente;


    // tem que ter os dois construtories esse o default e o outro... logo abaixo
    public Animal(){
        this.dataNascimento = new DataNascimento();
    }


    public Animal(String nome, LocalDate dataNascimento, EspecieEnum especie, Long clientId) {
        this.nome = nome;
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.especie = especie;
        this.cliente = new Cliente(clientId, null , null);
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

//    public Long getClientId() {
//
//        return clientId;
//    }
//
//    public void setClientId(Long clientId) {
//        this.clientId = clientId;
//    }

//    public List<Vacina> getVacinas() {
//        return vacinas;
//    }

//    public void setVacinas(List<Vacina> vacinas) {
//        this.vacinas = vacinas;
//    }
//
//    public List<Procedimento> getProcedimentos() {
//        return procedimentos;
//    }
//
//    public void setProcedimentos(List<Procedimento> procedimentos) {
//        this.procedimentos = procedimentos;
//    }



}
