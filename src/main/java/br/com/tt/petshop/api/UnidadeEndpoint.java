package br.com.tt.petshop.api;

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/*
Pode ser:
 1 - @Controller - se utilizado é obrigatório utilizar @ResponseBody
 2 - @RestController - equivale a primeira.
 3 - ResponseEntity<T>....
 */
@RestController
//ResquestMapping para se reutilizar a /unidades
@RequestMapping(value = "/unidades")

public class UnidadeEndpoint {

    private UnidadeService unidadeService;

    public UnidadeEndpoint(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    //    /unidades
//    @RequestMapping(method = RequestMethod.GET)  --> @GetMapping
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Unidade> findAll(){
        return unidadeService.findAll();
    }


//    /unidades/{id}
    /*
    NAO PODE TER DOIS METODOS PARA O MESMO ENDEREÇO no caso aqui: /unidades
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // para colocar variáveis como 'id' usa-se '{}'
    public ResponseEntity<Unidade> findById(@PathVariable Long id) {
        Optional<Unidade> unidadeOpt = unidadeService.findById(id);
        if (unidadeOpt.isPresent()) { // isPresent() = @return {@code true} if there is a value present, otherwise {@code false}
            return ResponseEntity.ok(unidadeOpt.get());
            //ResponseEntity faz referencia aos https... numero 404-NOT FOUND...etc;
        }
        return ResponseEntity.notFound().build();
    }


//    /unidades
    //Para 'create' é necessario utulizar o '@ResquestBody'
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Unidade unidade){
        Unidade unidadeCriada = unidadeService.create(unidade) ;
        URI uri = URI.create(String.format("/unidade/%d", unidadeCriada.getId()));
        return ResponseEntity.created(uri).build();
    }
    /*
    A URI can be further classified as a locator, a name, or both. The term “Uniform Resource Locator” (URL)
     refers to the subset of URIs that, in addition to identifying a resource, provide a means of locating the
      resource by describing its primary access mechanism (e.g., its network “location”).
     */

//    /unidades/{id}
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updade(@RequestBody Unidade unidade, @PathVariable Long id){


        unidade.setId(id);
        unidadeService.update(unidade);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
      unidadeService.delete(id);
      return ResponseEntity.noContent().build();
    }

}
