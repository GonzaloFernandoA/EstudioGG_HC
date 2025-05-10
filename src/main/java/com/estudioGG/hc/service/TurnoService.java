/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;


import com.estudioGG.hc.model.Turno;
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
public class TurnoService<T extends Identifiable> {
    
    private static final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private final S3RepositoryImpl<Turno> TurnoRepository;
    
    @Autowired
    private ClienteService clienteService; 
        
    @Autowired
    public TurnoService(S3RepositoryImpl<Turno> turnoRepository) {
        this.TurnoRepository = turnoRepository;
    }

    public Turno nuevo()
    {
        Turno turno = new Turno();

        return turno; 
    }
    
    public void guardar(Turno turno) {
        logger.info(turno.toJson());
        TurnoRepository.save(turno.getId(), turno);
    }

    public Turno obtener(String id) {
        Turno turno =  TurnoRepository.findByKey(id, Turno.class);
        return turno;
    }
    
    public List<Turno> obtenerTodos() {
        return TurnoRepository.findAll(Turno.class);
    }
}