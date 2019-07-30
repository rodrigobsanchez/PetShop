package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Repository é sinonimo de SINGLETON
@Repository
public class ClienteRepository {

    List<Cliente> clientes = new ArrayList<Cliente>(Arrays.asList(
            new Cliente(1L,"Josnei" , "12345678"),
            new Cliente(2L,"Fulano", "12245678"),
            new Cliente(3L,"Asdruba", "11125678")
    ));

    public void save(Cliente cliente){
        try {
            cliente.setId(SecureRandom.getInstanceStrong().nextLong());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        clientes.add(cliente);

    }
    public void delete(Cliente c){

        clientes.remove(c);
    }
    public List<Cliente> findAll(){

        return clientes;
    }

    public Cliente find(Long clientId) {
        for (Cliente cliente: clientes) {
            if(cliente.getId().equals(clientId)){
                return cliente;
            }
        }
        return null;
    }
}
