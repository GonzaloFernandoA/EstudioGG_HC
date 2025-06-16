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
        return new Turno();
    }
    
    public void guardar(Turno turno) {
        logger.info(turno.toJson());
        TurnoRepository.save(turno.getId(), turno, "Turno_out");
    }

    public void actualizar(Turno turno) {
        logger.info(turno.toJson());
        TurnoRepository.save(turno.getId(), turno );
    }

    public boolean eliminarPorDni(String dni) {
        logger.info("Eliminando turno por DNI: {}", dni);
        Turno turno = TurnoRepository.findByKey(dni, Turno.class);
        if (turno != null) {
            TurnoRepository.delete(turno.getId(), Turno.class);
            logger.info("Turno con DNI {} eliminado", dni);
            return true;
        } else {
            logger.warn("No se encontró un turno con DNI {}", dni);
            return false;
        }
    }

    public Turno obtener(String id) {
        Turno turno =  TurnoRepository.findByKey(id, Turno.class);
        return turno;
    }
    
    public List<Turno> obtenerTodos() {
        return TurnoRepository.findAll(Turno.class);
    }

    public void updateTemplate(String id, String template, String valor ) {
        Turno turno = obtener(id);
        logger.info("Updating template {} for turno with id {} and valor {}", template, id, valor);
        if (turno != null) {
            switch (template) {
                case "altaturno"  -> {
                    turno.setAlta(valor);
                    logger.info("Alta template updated for turno with id {}", id);
                    break;
                }
                case "recordatorio3" -> {
                    turno.setRecordaTresDias(valor);
                    logger.info("Recordatorio3 template updated for turno with id {}", id);
                    break;
                }
                case "recordatorio1" -> {
                    turno.setRecordaUnDia(valor);
                    logger.info("Recordatorio1 template updated for turno with id {}", id);
                    break;
                }
                case "postconfirmacion" -> {
                    turno.setConfirmacion(valor);
                    logger.info("Postconfirmacion template updated for turno with id {}", id);
                    break;
                }
                default -> logger.warn("Unknown template: {}", template);
            }
            actualizar(turno);
        } else {
            logger.error("Turno with id {} not found", id);
        }
    }
}