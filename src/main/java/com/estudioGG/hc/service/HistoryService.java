/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.model.Proceso;
import com.estudioGG.hc.utils.Categorias;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class HistoryService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(HistoryService.class);
    
    @Autowired
    Categorias categoria; 
    
    @Autowired
    HistoriaClinicaService historia;

    @Autowired
    DemandaService demandas;

    @Autowired
    EstudiosPeritoService estudios;

    @Autowired
    PericiaService pericias;

    public List<Proceso> getHistory(String dni) {

        List<Proceso> procesos = new ArrayList<>();
        List<HistoriaClinica> historias = historia.getAll(dni);

        logger.info("Cantidad de Historias :" + historias.size());
        
        historias.forEach(his -> {
            logger.info("Identificador:" + his.getId());
            
            Proceso proceso = new Proceso();
            proceso.setHistoria(his);
            
            List<String> linea = new ArrayList<>();
            his.getRegistros().forEach(reg -> 
            {
                    logger.info(reg.getCodigoParteCuerpo() + " - " + reg.getCodigoUbicacion());
                    linea.add(transformPartesCuerpo(reg.getCodigoParteCuerpo()) + " - " + transformUbicacion(reg.getCodigoUbicacion()));
            });
                    
            proceso.setLesiones(linea);
            proceso.setDemanda(demandas.obtener(his.getId()));
            proceso.setEstudios(estudios.obtener(his.getId()));
            proceso.setPericia(pericias.obtener(his.getId()));

            logger.info("tamaño" + proceso.getLesiones().size());
            
            procesos.add(proceso);
        });

        return procesos;
    }
    
    private String transformPartesCuerpo(String parte)
    {
        return categoria.getDescripcionPorCodigo( parte) ;
    }
    
        private String transformUbicacion(String parte)
    {
        return categoria.getDescripcionPorCodigoUbicacion(parte) ;
    }
}
