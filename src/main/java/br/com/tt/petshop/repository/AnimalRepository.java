package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // foi necessario criar esse metodo pois o animal tinha clientId como atributo. No caso do clienteRepository nao é necessario.
    List<Animal> findByClienteId(Long clientId);

    List<Animal> findByNome(String rex);

    List<Animal> findByDataNascimentoDataBetweenAndEspecieIs(LocalDate dataInicio, LocalDate dataFim, EspecieEnum especie);


}
