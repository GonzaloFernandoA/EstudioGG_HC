/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;


import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.Identifiable;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService<T extends Identifiable> {
    
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    
    private final S3RepositoryImpl<Cliente> ClienteRepository;
    
    @Autowired
    public ClienteService(S3RepositoryImpl<Cliente> ClienteRepository) {
        this.ClienteRepository = ClienteRepository;
    }

    public void guardar(Cliente cliente) {
        logger.info(cliente.toJson());
        ClienteRepository.save(cliente.getId(), cliente);
    }

    public Cliente obtener(String id) {
        Cliente cliente =  ClienteRepository.findByKey(id, Cliente.class);
        return cliente;
    }
    
    public List<Cliente> obtenerTodos() {
        return ClienteRepository.findAll(Cliente.class);
    }
}
