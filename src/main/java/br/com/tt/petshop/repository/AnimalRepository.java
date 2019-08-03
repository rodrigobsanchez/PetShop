package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // foi necessario criar esse metodo pois o animal tinha clientId como atributo. No caso do clienteRepository nao é necessario.
    List<Animal> findByClienteId(Long clientId);
//
//    List<Animal> animais = new ArrayList<Animal>(Arrays.asList(
//            new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1L),
//            new Animal("Nemo", LocalDate.now().minusMonths(1), EspecieEnum.PEIXE, 1L),
//            new Animal("Velociraptor", LocalDate.now().minusYears(10000), EspecieEnum.REPTIL, 1L)
//    ));
//
//
//
//
//    public List<Animal> listar(Long clienteId){
//        List<Animal> animaisDoCliente = new ArrayList<>();
//        for(Animal animal : animais){
//            if(animal.getClientId().equals(clienteId)){
//                animaisDoCliente.add(animal);
//            }
//        }
//        return animaisDoCliente;
//    }
//
//    public void save(Animal animal) {
//        animais.add(animal);
//    }
//
////    public void delete(Animal animal) {
////       animais.remove(animal);
////   }
//    public List<Animal> findAll(){
//        return animais;
//    }
}
