/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.Demanda;
import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class DemandaService {
    
    private final S3RepositoryImpl<Demanda> repository;
    private final ClienteService clienteService; 
    
    @Autowired
    public DemandaService(S3RepositoryImpl<Demanda> Repository, 
    ClienteService clienteService) {
        this.repository = Repository;
        this.clienteService = clienteService; 
    }

    public void guardar(Demanda entidad) {
        
        Cliente cliente = clienteService.obtener(entidad.getDni()); 
        if ( cliente == null ) 
        {
            
            throw new IllegalArgumentException("El DNI no pertenece a ningún cliente registrado.");
     
        } else {
        repository.save(entidad.getId(), entidad);
        }
        
    }

    public Demanda obtener(String id) {
        return repository.findByKey(id, Demanda.class);
    }
    
}
