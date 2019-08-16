package br.com.tt.petshop.service;

import br.com.tt.petshop.dto.AnimalDto;
import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.exception.ClienteNotFoundException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalService {

    //make it 'final' will make you do the constructor.

    private static final int TAMANHO_MININO_NOME = 3;

    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;
    private final ModelMapper mapper;

    public AnimalService(AnimalRepository animalRepository, ClienteService clienteService, ModelMapper mapper)
    {
        this.animalRepository = animalRepository;
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    public List<String> listarEspecies() {

        List<String> especies = new ArrayList<String>();
        for(EspecieEnum especie : EspecieEnum.values()){
            especies.add(especie.name());
        }
        return especies;
    }

    public List<Animal> listar(long clientId){
        //findbyClientId é criado na interface AnimalRepository... ATENCAO pois isso será a relcao com banco de dados.
        return animalRepository.findByClienteId(clientId);

    }

    public Animal salvar(@NotNull @Valid AnimalDto animalDto) throws BusinessException {
        Optional<Cliente> cliente = clienteService.findById(animalDto.getClienteId());
        Animal animal = mapper.map(animalDto, Animal.class);
        // .get() é uma função do Optional...é necessário o isPresent() function...
        animal.setCliente(cliente.orElseThrow(ClienteNotFoundException:: new));
        return salvar(animal);
    }
    /*
    @deprecaterd Utilizar o salvar(animalDto) que possui o id do cliente.
     */
    @Deprecated
    public Animal salvar(Animal animal) throws BusinessException {
        if(Objects.isNull(animal)){
            throw new IllegalArgumentException("Animal deve ser informado!");
        }

        validarSeDataNascimentoMenorOuIgualHoje(animal.getDataNascimento().getData());
        validarTamanhoMinimoNome(animal.getNome());
        clienteService.validarSeAdimplente(animal.getCliente().getId());

        return animalRepository.save(animal);
    }

    private void validarTamanhoMinimoNome(String nome) throws BusinessException {
        if(Objects.isNull(nome)){
            throw new BusinessException("Nome deve ser informado!");
        }

        if(nome.length() < TAMANHO_MININO_NOME){
            throw new BusinessException(
                    String.format(
                            "O nome deve conter ao menos %d caracteres!",
                            TAMANHO_MININO_NOME));
        }
    }

    private void validarSeDataNascimentoMenorOuIgualHoje(LocalDate dataNascimento) throws BusinessException {
        if (Objects.isNull(dataNascimento) ||
                LocalDate.now().isBefore(dataNascimento)) {
//            if(Objects.isNull(animal.getDataNascimento())
//                    || animal.getDataNascimento().isAfter(LocalDate.now())){
            throw new BusinessException("A data de nascimento deve ser anterior ou igual a hoje!");
        }

    }
    public List<Animal> listarByExample(Optional<Long> clienteId, Optional<String> nome) {

        Animal animal = new Animal();
        if (clienteId.isPresent()) {
            animal.setCliente(new Cliente(clienteId.get()));
        }
        if (nome.isPresent()) {
            animal.setNome(nome.get());
        }
        return animalRepository.findAll(Example.of(animal), Sort.by("nome"));
    }

    public void excluirAnimal(Cliente cliente) {
        Animal animal = new Animal();
        animalRepository.delete(animal);
    }
}


