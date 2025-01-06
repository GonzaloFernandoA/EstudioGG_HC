/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.controller.HistoriaClinicaController;
import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.model.Identifiable;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class HistoriaClinicaService<T extends Identifiable> {

    private static final Logger logger = LoggerFactory.getLogger(HistoriaClinicaService.class);
    private final S3RepositoryImpl<HistoriaClinica> _hcRepository;
    private final ClienteService clienteService;

    @Autowired
    public HistoriaClinicaService(S3RepositoryImpl<HistoriaClinica> HistoriaClinicaRepository,
            ClienteService clienteService) {
        this._hcRepository = HistoriaClinicaRepository;
        this.clienteService = clienteService;
    }

    public void Valid(HistoriaClinica entidad) {
        this.validDni(entidad);
    }

    public void validDni(HistoriaClinica entidad) {

        Cliente cliente = clienteService.obtener(entidad.getDni());

        if (cliente == null) {
            logger.error("El DNI "+ entidad.getDni() + " no pertenece a ningún cliente registrado.");
            throw new IllegalArgumentException("El DNI "+ entidad.getDni() + " no pertenece a ningún cliente registrado.");
        }
            
    }
    public void guardar(HistoriaClinica entidad) {

        this.Valid(entidad);
        _hcRepository.save(entidad.getId(), entidad);

    }

    public List<HistoriaClinica> getAll(String dni)
    {
        return _hcRepository.findAll(dni, HistoriaClinica.class);
    }
    
    public HistoriaClinica obtener(String id) {
        return _hcRepository.findByKey(id, HistoriaClinica.class);
    }
}
