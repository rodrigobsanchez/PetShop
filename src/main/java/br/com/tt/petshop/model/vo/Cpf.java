package br.com.tt.petshop.model.vo;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cpf {

   @Column(name="CPF_CLIENTE")
    private String valor;

    public Cpf() {

    }

    public Cpf(String valor) {
        this.valor = valor;
    }

    public boolean isValid(){
        String temp = valor;
        return valor != null && temp.replaceAll("\\D", "").length() == 11;

    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return getValor();
    }
}