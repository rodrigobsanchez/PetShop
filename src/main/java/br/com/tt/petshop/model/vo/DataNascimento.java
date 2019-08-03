package br.com.tt.petshop.model.vo;


import br.com.tt.petshop.exception.BusinessException;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable


public class DataNascimento {
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    @Column (name = "data_nascimento")
    private LocalDate data;



    public DataNascimento() {
    }

    public DataNascimento(LocalDate data) {
        this.data = data;
    }

    public boolean isValida() throws BusinessException{
        if(Objects.isNull(data) || LocalDate.now().isBefore(data)){
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "" + data;
    }
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
