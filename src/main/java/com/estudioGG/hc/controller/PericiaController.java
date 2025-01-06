/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.Demanda;
import com.estudioGG.hc.model.Pericia;
import com.estudioGG.hc.service.DemandaService;
import com.estudioGG.hc.service.PericiaService;
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
@RequestMapping("/pericia")
public class PericiaController {

    private static final Logger logger = LoggerFactory.getLogger(PericiaController.class);
    private final PericiaService Service;
    
    @Autowired
    Categorias categorias; 

    @Autowired
    public PericiaController(PericiaService service) {
        this.Service = service;
    }

    // Muestra el formulario para crear una nueva historia clínica
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        // Creamos una nueva instancia para inicializar los datos
        model.addAttribute("pericia", new Pericia());
        model.addAttribute("opcionesParte", categorias.getPartesCuerpo());
        model.addAttribute("opcionesUbicacion", categorias.getUbicaciones());

        return "pericia/formulario";
    }

    // Recibe los datos enviados desde el formulario
    @PostMapping
    //  public String guardarHistoriaClinica(@ModelAttribute ModelAttribute model) {
    public String guardar(@ModelAttribute("pericia") Pericia entidad, Model model) {
        logger.info(entidad.toJson());

        try {
            Service.guardar(entidad);
            model.addAttribute("successMessage", "Pericia guardada exitosamente.");
            return "redirect:/pericia/nuevo"; // Redirigir al listado o una página de éxito
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "pericia/formulario"; // Volver al formulario si hay error
        }
    }

}
