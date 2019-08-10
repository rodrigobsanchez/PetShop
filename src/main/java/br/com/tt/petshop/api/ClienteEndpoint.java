package br.com.tt.petshop.api;


import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")

public class ClienteEndpoint {

    private final ClienteService clienteService;


    public ClienteEndpoint(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Cliente cliente) throws BusinessException {

        URI uri = URI.create(String.format("/clientes/%d",
                clienteService.adicionar(cliente).getId()));
        return ResponseEntity.created(uri).build();
     }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = clienteService.findById(id);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@RequestBody Cliente cliente, @PathVariable Long id) throws BusinessException {
        clienteService.update(id, cliente);
        return ResponseEntity.noContent().build();
    }

}
