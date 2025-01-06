/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.EstudiosPerito;
import com.estudioGG.hc.service.EstudiosPeritoService;
import com.estudioGG.hc.utils.Categorias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Gonzalo_Avalos
 */
@Controller
@RequestMapping("/estudiosperito")
public class EstudiosPeritoController {

    private static final Logger logger = LoggerFactory.getLogger(EstudiosPeritoController.class);
    private final EstudiosPeritoService Service;
    
    @Autowired
    Categorias categorias; 

    @Autowired
    public EstudiosPeritoController(EstudiosPeritoService service) {
        this.Service = service;
    }

    // Muestra el formulario para crear una nueva historia clínica
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        // Creamos una nueva instancia para inicializar los datos
        model.addAttribute("estudiosperito", new EstudiosPerito());
        model.addAttribute("opcionesParte", categorias.getPartesCuerpo());
        model.addAttribute("opcionesUbicacion", categorias.getUbicaciones());

        return "estudiosperito/formulario";
    }

    // Recibe los datos enviados desde el formulario
    @PostMapping
    //  public String guardarHistoriaClinica(@ModelAttribute ModelAttribute model) {
    public String guardar(@ModelAttribute("estudiosperito") EstudiosPerito entity, Model model) {
        logger.info(entity.toJson());

        try {
            Service.guardar(entity);
            model.addAttribute("successMessage", "Datos guardados exitosamente.");
            return "redirect:/demanda/nuevo"; // Redirigir al listado o una página de éxito
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "estudiosperito/formulario"; // Volver al formulario si hay error
        }
    }

}
