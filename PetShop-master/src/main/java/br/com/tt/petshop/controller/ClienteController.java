package br.com.tt.petshop.controller;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ClienteController {

      private final ClienteService service;

    public ClienteController(ClienteService cs) {

        this.service = cs;
    }

    // will get the homepage = '/'
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("sistema", "PetShop");
        model.addAttribute("clientes", service.listar());
        return "index";
    }
    // getmapping ira carregar a pagina web.../cliente-adicionar
    @GetMapping("/cliente-adicionar")
    public String paginaAdcionar(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-adicionar";
    }

    @PostMapping("/cliente-form")
    public String clienteForm(Cliente novoCliente, Model model)  {
        try {
            service.adicionar(novoCliente);
        } catch (BusinessException e) {
            model.addAttribute("erro", e.getMessage());
        }
        return "cliente-adicionar";
    }

    @GetMapping("/cliente-excluir")   //?id={id}
    public RedirectView clienteExcluir(@RequestParam long id){
        service.remover(id);
        return new RedirectView("/");
    }

}
