/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.model.Identifiable;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class HistoriaClinicaService<T extends Identifiable> {
    
    private final S3RepositoryImpl<HistoriaClinica> _hcRepository;
    private final ClienteService clienteService; 
    
    @Autowired
    public HistoriaClinicaService(S3RepositoryImpl<HistoriaClinica> HistoriaClinicaRepository, 
    ClienteService clienteService) {
        this._hcRepository = HistoriaClinicaRepository;
        this.clienteService = clienteService; 
    }

    public void guardar(HistoriaClinica entidad) {
        
        Cliente cliente = clienteService.obtener(entidad.getDni()); 
        
        if ( cliente == null ) 
        {
            
            throw new IllegalArgumentException("El DNI no pertenece a ningún cliente registrado.");
     
        } else {
        _hcRepository.save(entidad.getId(), entidad);
        }
        
    }

    public HistoriaClinica obtener(String id) {
        return _hcRepository.findByKey(id, HistoriaClinica.class);
    }
}
