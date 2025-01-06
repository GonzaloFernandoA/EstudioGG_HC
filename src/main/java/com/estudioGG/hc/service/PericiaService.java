/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.service;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.EstudiosPerito;
import com.estudioGG.hc.model.Pericia;
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
public class PericiaService {

    private final S3RepositoryImpl<Pericia> repository;
    private final ClienteService clienteService;
    private final EstudiosPeritoService _estudioPeritoService;

    @Autowired
    ListaValidator validador;

    @Autowired
    public PericiaService(S3RepositoryImpl<Pericia> Repository,
            ClienteService clienteService, EstudiosPeritoService epService) {
        this.repository = Repository;
        this.clienteService = clienteService;
        this._estudioPeritoService = epService;
    }

    public void guardar(Pericia entidad) {

        this.Valid(entidad);
        repository.save(entidad.getId(), entidad);

    }

    public void Valid(Pericia entidad) {
        EstudiosPerito ep = _estudioPeritoService.obtener(entidad.getId());
        if (ep == null) {

            throw new IllegalArgumentException("No existe Estudios cargada para ." + entidad.getDni() + " con la fecha " + entidad.getFecha());
        }

        if (validador.validarListasIgualesSinOrden(entidad.getRegistros(), ep.getRegistros())) {
        } else {
            throw new IllegalArgumentException("las lesiones cargadas no concuerdan con los Estudios Perito");
        }
    }

    public List<Pericia> getAll(String dni) {
        return repository.findAll(dni, Pericia.class);
    }

    public Pericia obtener(String id) {
        return repository.findByKey(id, Pericia.class);
    }

}
