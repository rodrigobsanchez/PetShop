package br.com.tt.petshop.service;


import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
//ou MockitoAnnotations.initMocks(this)
public class AnimalServiceTest {

    private static final int TAMANHO_MININO_NOME = 3;
    private AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private ClienteService clienteService;

    @Before
    public void setUp(){
        animalService = new AnimalService(animalRepository,clienteService);
    }

    @Test
    public void deverriaRetornarListaVazia(){
        animalService = new AnimalService(animalRepository,clienteService);
        List<Animal> animais = animalService.listar(1L);
       // animais.add( new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1L));
        assertNotNull("A lista nao deveria ser nula",animais);

        assertEquals("A lista deve conter uma lista vazia", 0, animais.size());
        verify(animalRepository, times(1)).listar(1L);
    }
    @Test
    public void deveriaRetornarListComAnimais(){
        //apenas se usa 'when' 'verify' ou 'NoMoreIterations' se usa no mock ou seja as classes que a classe que estamos testando depende.
        //Arrange
        List<Animal> listaCliente01 = Arrays.asList(
                new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1L),
                new Animal("Nemo", LocalDate.now().minusMonths(1), EspecieEnum.PEIXE, 1L));
        List<Animal> listaCliente02 = Arrays.asList(
                new Animal("Velociraptor", LocalDate.now().minusYears(10000), EspecieEnum.REPTIL, 2L));
        Mockito.when(animalRepository.listar(1L)).thenReturn(listaCliente01);
        Mockito.when(animalRepository.listar(2L)).thenReturn(listaCliente02);

        //Act
        List<Animal> animaisCliente01 = animalService.listar(1L);
        List<Animal> animaisCliente02 = animalService.listar(2L);
        assertEquals("Deveria retornar a lista do cliente01", listaCliente01, animaisCliente01);
        assertEquals("Deveria retornar a lista do cliente02", listaCliente02, animaisCliente02);
    }

    @Test
    public void deveriaListardeEspecies(){
        List<String> list = animalService.listarEspecies();

        assertArrayEquals("Deveria ter o mesmo array de especies com 3" , list.toArray(),
                new String[]{
                        //é exigido na mesma ordem que esses enum foram escritos
                EspecieEnum.REPTIL.name(), EspecieEnum.MAMIFERO.name(), EspecieEnum.PEIXE.name()
                }
                );





    }

    @Test
    public void deveriaAdicionarAnimal() throws BusinessException {
        Animal a1 = new Animal("Velociraptor", LocalDate.now().minusYears(10000), EspecieEnum.REPTIL, 1L);

        animalService.salvar(a1);

        Mockito.verify(animalRepository).save(a1);


    }
    @Test
    public void deveriaLancarExcecaoDeNomeNull(){
        Animal a1 = new Animal(null, LocalDate.now(), EspecieEnum.REPTIL, 1L);
        a1 = null;
        try {
            animalService.salvar(a1);
            fail("Deveria ter lançado exceção...");
        } catch (IllegalArgumentException | BusinessException e) {
            assertEquals("Animal deve ser informado!", e.getMessage());
        }
    }
    @Test
    public void deveriaLancarExcecaodaDATA(){
        Animal a1 = new Animal("Velociraptor", LocalDate.now().plusDays(1), EspecieEnum.REPTIL, 1L);
        try {
            animalService.salvar(a1);
            fail("Deveria ter lançado exceção...");
        } catch (BusinessException e) {
            assertEquals("A data de nascimento deve ser anterior ou igual a hoje!", e.getMessage());
        }
    }
    @Test
    public void deveriaLancarExcecaodeNomeNull(){
        Animal a1 = new Animal(null, LocalDate.now(), EspecieEnum.REPTIL, 1L);

        try {
            animalService.salvar(a1);
            fail("Deveria ter lançado exceção...");
        } catch (BusinessException e) {
            assertEquals("Nome deve ser informado!", e.getMessage());
        }
    }
    @Test
    public void deveriaLancarExcecaodeTamanhoNome(){
        Animal a1 = new Animal("Ve", LocalDate.now(), EspecieEnum.REPTIL, 1L);

        try {
            animalService.salvar(a1);
            fail("Deveria ter lançado exceção...");
        } catch (BusinessException e) {
            assertEquals(String.format(
                    "O nome deve conter ao menos %d caracteres!",
                    TAMANHO_MININO_NOME), e.getMessage());
        }
    }

    @Test
    public void deveriaFalharClienteInadinplente() throws BusinessException{
        Animal a1 = new Animal("bababs", LocalDate.now(), EspecieEnum.PEIXE, 10L );
        Cliente novoCliente = new Cliente(10L, "André Ruim", "111.222.333-35");
        boolean test = true;
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteService.listar();
        clientes.add(novoCliente);
        clientes.get(0).setInadimplente(test);
        Cliente c = clientes.get(0);
        animalService.salvar(a1);
        // Assim que se faz em METODOS VOID.
        //doThrow(BusinessException.class).when(clienteService).validarSeAdimplente(10L);
    }
}
