package br.com.tt.petshop.api;

import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.dto.factory.ClienteDtoFactory;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.ModelMap;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class) // NAO confundir com o MockitoRunner... seria um Spring Runner Test.
@WebMvcTest (controllers = ClienteEndpoint.class)
// @WebMvcTest para utilizar testes jUnit 4 numa app String boot *** APenas para testar o Controller.
@Import(ModelMapperConfig.class)
public class ClienteEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;
    @MockBean
    private ModelMapper mapper;

    @Test
    public void deveRetornarListaClienteComSucesso() throws Exception {
        //chamada HTTP;;
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
        ).andExpect(MockMvcResultMatchers.status().is(200))
               .andExpect(content().string("[]"))
                  .andDo(MockMvcResultHandlers.print());

        /*
        a rota seria /clientes... Controller
         */
    }

    @Test
    public void deveRetornarListaComClienteFulano() throws Exception {

        Mockito.when(clienteService.listar()).thenReturn((Arrays.asList(new Cliente(77L, "Fulano Silva", "000.111.222-33"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(CoreMatchers.equalTo(77)))
        .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void deveCriarClienteComSucesso() throws Exception {
        Cliente clienteSalvar = new Cliente (56L, "Fulano Silva Silva", "111.222.333-44");

        Mockito.when(
                clienteService.adicionar(clienteSalvar)).thenReturn(clienteSalvar);
          ClienteDto clienteDto = ClienteDtoFactory.from(clienteSalvar);

        byte[] objectToJson = new ObjectMapper().writeValueAsBytes(new Cliente());
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                .content(objectToJson));
    }
}