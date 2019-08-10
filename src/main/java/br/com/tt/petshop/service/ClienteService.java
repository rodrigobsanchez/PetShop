package br.com.tt.petshop.service;

import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.vo.Cpf;
import br.com.tt.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }


    public void remover(long id){
        //TODO alterar no JPA
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listar()
    {
        return clienteRepository.findAll();
    }

    public Cliente adicionar(Cliente novoCliente) throws BusinessException {

        validaNome(novoCliente);
        validaCpf(novoCliente);
        checkUser(novoCliente);
        return clienteRepository.save(novoCliente);
    }

    private void checkUser(Cliente novoCliente) throws BusinessException {
        List<Cliente> temp = new ArrayList<>();
        temp = clienteRepository.findAll();
        for(Cliente c : temp){
            if(c.getNome().equals(novoCliente.getNome()) && c.getCpf().equals(novoCliente.getCpf())){
                throw new BusinessException("Esse cliente já possui cadastro!!");
            }
        }
    }

    private void validaNome(Cliente novoCliente) throws BusinessException {
        /*
        Necessario ter todos os getters and setters em ordem senao nao ocorre a atribuicao de um valor a variavel.
         */
        //checking if qualquer coisa for null;;; t
        if(Objects.isNull(novoCliente) || Objects.isNull(novoCliente.getNome())){
            throw new BusinessException("Nome deve ser informado.");
        }

        String[] parts = novoCliente.getNome().split(" ");
        if(parts.length < 2) {
            throw new BusinessException("Informe seu nome completo.");
        }
        //verifying each name length...
        for (String parte : parts){
            if(parte.length() < 2){
                throw new BusinessException("Informe seu nome sem abreviações");
            }
        }
    }


    private void validaCpf(Cliente novoCliente) throws BusinessException {
           Cpf cpf = novoCliente.getCpf();

        if (Objects.isNull(cpf) || Objects.isNull(cpf.getValor())) {
            throw new BusinessException("Informe seu CPF!!.");
        }
            // .replaceAll para retirar os special character '.' '-' do CPF.
           String temp = novoCliente.getCpf().getValor().replaceAll("\\D", "");
        if(temp.length() != 11){
            throw new BusinessException("Informe seu CPF com 11 dígitos");
        }
    }




    public void validarSeAdimplente(Long clientId) throws BusinessException {
        Cliente cliente = clienteRepository.getOne(clientId);

        if(cliente.isInadimplente()){
            throw new BusinessException("Cliente não está adimplente!");
        }
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
    public void update(Long id, Cliente cliente) throws BusinessException {
        Optional<Cliente> c =  this.findById(id);
        if(c.isPresent()){
            Cliente clienteSalvo = c.get();
            clienteSalvo.setNome("NovoNome");
            clienteSalvo.setInadimplente(clienteSalvo.isInadimplente());
            clienteRepository.save(cliente);

        }
    }
}
