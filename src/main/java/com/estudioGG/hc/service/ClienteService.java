/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;


import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.Identifiable;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService<T extends Identifiable> {

    private final S3RepositoryImpl<Cliente> ClienteRepository;
    
    @Autowired
    public ClienteService(S3RepositoryImpl<Cliente> ClienteRepository) {
        this.ClienteRepository = ClienteRepository;
    }

    public void guardar(Cliente cliente) {
        ClienteRepository.save(cliente.getId(), cliente);
    }

    public Cliente obtener(String id) {
        return ClienteRepository.findByKey(id, Cliente.class);
    }
    
    public List<Cliente> obtenerTodos() {
        return ClienteRepository.findAll(Cliente.class.getSimpleName(),Cliente.class);
    }
}
