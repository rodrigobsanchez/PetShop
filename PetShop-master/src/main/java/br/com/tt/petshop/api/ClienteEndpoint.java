package br.com.tt.petshop.api;


import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")

public class ClienteEndpoint {

    private final ClienteService clienteService;
    private final ModelMapper mapper;


    public ClienteEndpoint(ClienteService clienteService, ModelMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDto>> findAll(){
        return ResponseEntity.ok(clienteService.listar().stream()
        .map( c -> mapper.map(c, ClienteDto.class)).collect(Collectors.toList()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody @Valid ClienteDto clienteDto) throws BusinessException {
         // @Valid é necessario por causa do javax.validation annotations @NotBlack, @Size
        URI uri = URI.create(String.format("/clientes/%d",
                clienteService.adicionar(mapper.map(clienteDto, Cliente.class)).getId()));
        return ResponseEntity.created(uri).build();
     }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteService.findById(id);
        if (clienteOpt.isPresent()) {
            ClienteDto dto = mapper.map(clienteOpt.get(), ClienteDto.class);
            return ResponseEntity.ok(dto);

        }
        return ResponseEntity.notFound().build();

    }

    /*
       Aqui no findById ele irá converter esse ClienteDto no Cliente oficinal... Esse procedimento é feito para nao expor a entidade real do programa
    a qual seria uma 'pratica ruim'
     */

    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@RequestBody Cliente cliente, @PathVariable Long id) throws BusinessException {
        clienteService.update(id, cliente);
        return ResponseEntity.noContent().build();
    }

}
