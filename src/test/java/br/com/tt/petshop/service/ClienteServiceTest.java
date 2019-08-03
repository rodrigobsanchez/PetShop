package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)

public class ClienteServiceTest {

    //@Test já automatiza a execucao.

    //cliar a classe alvo... que possui meteodo a ser testado

    private ClienteService clienteService;


    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setUp(){

        clienteService = new ClienteService(clienteRepository);
    }

    @Test
   public void deveriaRetornarListaVazia(){
       // clienteRepository = new ClienteRepository();
        clienteService = new ClienteService(clienteRepository);
        // Arrange

        //Act
    List<Cliente> clientes = clienteService.listar();

    //Assert.assertNotNull(clientes);
        //Assert...
    assertNotNull("A lista nao deveria ser nula",clientes);
   // Assert.assertEquals(0, clientes.size());
    assertEquals("A lista deve conter 3 objetos CLientes",0, clientes.size());


    }



    @Test
    public void deveriaRetornarListaComClientes() {

        //Arrange - Setup!
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente(1L,"Fulano", "000.111.222-33"));
        listaClientes.add(new Cliente(2L,"Beatriz", "000.111.222-99"));
        clienteRepository.save(listaClientes.get(0));
        clienteRepository.save(listaClientes.get(1));
        Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes);
        // Act - Execução
        List<Cliente> clientes = clienteService.listar();
        //Assert - Verificação
        assertEquals("Deveria retornar dois clientes", 2, clientes.size());

    }
    @Test
    public void deveriaRemoverComSucesso(){
        //Act
        clienteService.remover(12L);

        //Assert
        Cliente clienteDeletado = new Cliente (12L, null, null);
        //usar verify sempre se uma classe utilizou a outra.
        Mockito.verify(clienteRepository).delete(clienteDeletado);
        //Mockito.verifyNoMoreInteractions(clienteRepository);

    }


    @Test
    public void deveriaAdicionarComSucesso() throws BusinessException {
        //Arrange
        Cliente novoCliente = new Cliente(10L, "Joao Almeida", "11122233345");

        //Act
        clienteService.adicionar(novoCliente);

       //Verify
        Mockito.verify(clienteRepository).save(novoCliente);

       // Mockito.verifyNoMoreInteractions(clienteRepository);
    }

    @Test
    public void deveriaLancarExcecaoDeNomeNull(){
        Cliente novoCliente = new Cliente(10L, null, "111.222.333-45");

        try {
            clienteService.adicionar(novoCliente);
            fail("Deveria ter lançado exceção nome null");
        } catch (BusinessException e) {
            assertEquals("Nome deve ser informado.", e.getMessage());
        }
    }
   @Test
   public void deveriaLancarExcecaodeDoisNomes(){
       Cliente novoCliente = new Cliente(10L, "TARDELLI", "111.222.333-45");

       try {
           clienteService.adicionar(novoCliente);
           fail("Deveria ter lançado exceção nome completo");
       } catch (BusinessException e) {
           // nesse assertEquals a mensagem tem que se a mesmas que e.getMessage retorna.
           assertEquals("Informe seu nome completo.", e.getMessage());
       }
   }

   @Test
   public void deveriaLancarExcecaoDasAbreviações(){
       Cliente novoCliente = new Cliente(10L, "João A", "111.222.333-45");

       try{
           clienteService.adicionar(novoCliente);
           fail("Deveria ter lançado exceção da abreviação");
       } catch (BusinessException e) {
           assertEquals("Informe seu nome sem abreviações", e.getMessage());
       }
   }

    @Test
    public void deveriaLancarExcecaoDeCPFNull(){
        Cliente novoCliente = new Cliente(10L, "André Ruim", null);

        try {
            clienteService.adicionar(novoCliente);
            fail("Deveria ter lançado exceção cpf null");
        } catch (BusinessException e) {
            assertEquals("Informe seu CPF!!.", e.getMessage());
        }

    }
    @Test
    public void deveriaLancarExcecaoCpfLength(){
        Cliente novoCliente = new Cliente(10L, "André Ruim", "12012312366");

        try {
            clienteService.adicionar(novoCliente);
        } catch (BusinessException e){
            assertEquals("Informe seu CPF com 11 dígitos", e.getMessage());
        }
    }

    @Test
    public void deveriaLancarBusinessException() {
        try {
            clienteService.adicionar(null);
            fail("Deveria ter lançado exceção!");
        } catch (BusinessException e) {
            assertNotNull("Nome deveria ter 2 partes", e.getMessage());
        }
    }
    @Test
    public void deveriaLancarExceptionDeInadimplente() {

        Cliente novoCliente = new Cliente(10L, "André Ruim", "111.222.333-35");
        boolean test = true;

        List<Cliente> clientes = new ArrayList<Cliente>(Arrays.asList(
                new Cliente(1L,"Josnei" , "12345678"),
                new Cliente(2L,"Fulano", "12245678"),
                new Cliente(3L,"Asdruba", "11125678")
        ));
        clientes = clienteService.listar();
        clientes.add(novoCliente);
        clientes.get(0).setInadimplente(test);
        Cliente c = clientes.get(0);
        Mockito.when(clienteRepository.getOne(10L)).thenReturn(c);
        //Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
        try {
            clienteService.validarSeAdimplente(10L);
        } catch (BusinessException e) {
           assertEquals("Cliente não está adimplente!", e.getMessage());
        }

    }
}