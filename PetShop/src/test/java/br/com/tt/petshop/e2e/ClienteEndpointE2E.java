package br.com.tt.petshop.e2e;

import br.com.tt.petshop.repository.ClienteRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteEndpointE2E {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @Sql("classpath:e2e/insere_fulano.sql")
    public void obtemClienteFulanoComSucesso() throws Exception {

//        Cliente clienteSalvo = clienteRepository.save(new Cliente(1L, "Fulano da Silva", "111.222.333-55"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/"+clienteSalvo.getId()))
//                .andExpect(jsonPath("$.id").value(CoreMatchers.equalTo(clienteSalvo.getId())));


        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/66"))
                .andExpect(jsonPath("$.id").value(CoreMatchers.equalTo(66)));
    }


}

