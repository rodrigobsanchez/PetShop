package br.com.tt.petshop.controller;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animais-listar")
    public String listar(Model model, @RequestParam Long clientId){

        model.addAttribute("animais", animalService.listar(clientId));

        return "animais-listar";
    }


    //@ "" faz referencia ao nome do arquivo html.
    @GetMapping("/animais-adicionar")
    public String adicionar(Model model){

        model.addAttribute("especies", listarEspecies());
        if(model.containsAttribute("animal") == false){
            model.addAttribute("animal", new Animal());
        }

        return "/animais-adicionar";
    }

    @PostMapping("/animais-form")
    public String salvar(Model model, Animal animal){

        try {
            animalService.salvar(animal);
            return String.format("redirect:/animais-listar?clientId=%s", animal.getClientId());

        } catch (BusinessException e) {
            model.addAttribute("erro", e.getMessage());

            return adicionar(model);
        }
    }

//    @GetMapping("/animal-excluir")   //?id={id}
//    public RedirectView animalExcluir(@RequestParam Long clientId){
//        Animal animal = new Animal();
//        animal.setClientId(clientId);
//
//        animalService.remover(name);
//        return new RedirectView("/");
//    }

    private List<String> listarEspecies() {
        return animalService.listarEspecies();
    }

}















































//package br.com.tt.petshop.controller;
//
//import br.com.tt.petshop.exception.BusinessException;
//import br.com.tt.petshop.model.Animal;
//import br.com.tt.petshop.service.AnimalService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//public class AnimalController {
//
//      private final AnimalService animalService;
//
//    public AnimalController(AnimalService animalService) {
//        this.animalService = animalService;
//    }
//
//    @GetMapping("/animais-listar")
//    public String listar(Model model, @RequestParam long clienteId){
//
//        model.addAttribute("animais", animalService.listar(clienteId));
//        return "animais-listar";
//    }
//
//    private List<String> listarEspecies() {
//        return animalService.listarEspecies();
//    }
//
//
//    @GetMapping("/animais-adicionar")
//    public String adicionar(Model model){
//
//        model.addAttribute("especies", listarEspecies());
//        if(model.containsAttribute("animal") == false){
//            model.addAttribute("animal", new Animal());
//        }
//
//        return "/animais-adicionar";
//    }
////    @GetMapping("/animal-form")
////    public String salvar(Animal animal, Model model){
//////        model.addAttribute("animal", new Animal());
//////        model.addAttribute("especies", listarEspecies());
////        return "/animal-adicionar";
////    }
//
//    @PostMapping("/animais-form")
//    public String salvar(Model model, Animal animal) throws BusinessException{
//
//        try {
//           animalService.salvar(animal);
//            return String.format("redirect:/animais-listar?clientId=%s", animal.getClientId());
//
//       } catch (BusinessException e) {
//           model.addAttribute("erro", e.getMessage());
//            return adicionar(model);
//       }
//   }
//
//
//
////
////    @GetMapping("/animal-adicionar")
////    public String adicionar(Model model)  {
////        model.addAttribute("animal", new Animal());
////        return "/animal-adicionar";
////    }
////    private List<String> listarEspecies(){
////        return animalService.listarEspecies();
////
////    }
////    //it was @PostMapping and i couldn't accsess animal-adicionar.
//
////
////    //teste,,,
////    @PostMapping("/animal-form")
////    public String animalForm(Animal animal, Model model){
////
////        animalService.adicionar(animal);
////        return "/animal-adicionar";
//
//}
