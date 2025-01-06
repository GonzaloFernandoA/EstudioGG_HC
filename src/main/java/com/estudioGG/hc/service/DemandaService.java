/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.Demanda;
import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import com.estudioGG.hc.utils.ListaValidator;
import java.util.List;
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
    private final HistoriaClinicaService _hcService; 
    
    @Autowired
    ListaValidator validador ;
    
    @Autowired
    public DemandaService(S3RepositoryImpl<Demanda> Repository, 
    ClienteService clienteService, HistoriaClinicaService hcService) {
        this.repository = Repository;
        this.clienteService = clienteService; 
        this._hcService = hcService;
    }

    public void guardar(Demanda entidad) {
        
        this.valid(entidad);
        repository.save(entidad.getId(), entidad);
        
    }

 
    public void valid(Demanda entidad)
    {
        HistoriaClinica hc = _hcService.obtener(entidad.getId());
        if ( hc == null ) 
            throw new IllegalArgumentException("No existe Historia Clinica cargada para ." + entidad.getDni() + " con la fecha " + entidad.getFecha());
        
        
        if ( validador.validarListasIgualesSinOrden(entidad.getRegistros(), hc.getRegistros()))
        {} else  throw new IllegalArgumentException("las lesiones cargadas no concuerdan con la Historia Clinica");
        
        
        
    }
    
    public List<Demanda> getAll(String dni)
    {
        return repository.findAll(dni, Demanda.class);
    }
    
    
    public Demanda obtener(String id) {
        return repository.findByKey(id, Demanda.class);
    }
    
}
