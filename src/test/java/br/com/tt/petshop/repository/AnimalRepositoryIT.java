package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AnimalRepositoryIT {

    @Autowired
    private AnimalRepository animalRepository;


    @Test
    public void deveriaRetornarListaVazia(){
        List<Animal> lista = animalRepository.findByClienteId(1L);
        assertEquals("Deveria ser lista vazia", 0, lista.size());
    }

    @Test
    @Sql("classpath:insere_rex.sql")
    public void deveriaRetornarUmAnimal(){
        List<Animal> list = animalRepository.findByClienteId(133L);
        assertEquals("Deveria retornar um animal", 1, list.size());
        Animal rex = list.get(0);
        assertEquals("O nome deveria ser Rex", "Rex", rex.getNome());
        assertEquals("O cliente deveria ser o 123", Long.valueOf(133), rex.getCliente().getId());
        assertEquals("O animal deveria ser um mamífero", EspecieEnum.MAMIFERO, rex.getEspecie());
    }

    @Test
    public void findByClienteId() {
    }
}