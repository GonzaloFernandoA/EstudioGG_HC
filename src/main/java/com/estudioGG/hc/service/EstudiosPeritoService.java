/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.Demanda;
import com.estudioGG.hc.model.EstudiosPerito;
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
public class EstudiosPeritoService {
    
    private final S3RepositoryImpl<EstudiosPerito> repository;
    private final ClienteService clienteService; 
    private final DemandaService _demandaService; 
    
        @Autowired
    ListaValidator validador ;
    
    @Autowired
    public EstudiosPeritoService(S3RepositoryImpl<EstudiosPerito> Repository, 
    ClienteService clienteService, DemandaService demandaService) {
        this.repository = Repository;
        this.clienteService = clienteService; 
        this._demandaService = demandaService;
    }

    public void guardar(EstudiosPerito entidad) {
        
        this.valid(entidad);
        repository.save(entidad.getId(), entidad);
       
        
    }
    
    
        public void valid(EstudiosPerito entidad)
    {
        Demanda demanda = _demandaService.obtener(entidad.getId());
        if ( demanda == null ) 
            throw new IllegalArgumentException("No existe Demanda cargada para ." + entidad.getDni() + " con la fecha " + entidad.getFecha());
        
                if ( validador.validarListasIgualesSinOrden(entidad.getRegistros(), demanda.getRegistros()))
        {} else  throw new IllegalArgumentException("las lesiones cargadas no concuerdan con la Demanda");
        
        
    }
        
            public List<EstudiosPerito> getAll(String dni)
    {
        return repository.findAll(dni, EstudiosPerito.class);
    }

    public EstudiosPerito obtener(String id) {
        return repository.findByKey(id, EstudiosPerito.class);
    }
    
}