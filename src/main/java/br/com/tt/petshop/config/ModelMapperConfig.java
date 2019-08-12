package br.com.tt.petshop.config;

import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.vo.Cpf;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getBean(){
        System.out.println("Iniciei o modelmapper");

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Cliente.class, ClienteDto.class) // continuação abaixo...
                .addMapping(cliente -> cliente.getCpf().getValor(), ClienteDto::setCpf);

        // para criar uma variavel no intelliJ o shortcut é 'Ctrl+Alt+V'

        modelMapper.createTypeMap(ClienteDto.class, Cliente.class).addMapping(dto -> new Cpf(dto.getCpf()), Cliente :: setCpf);

        return modelMapper;

    }
}
