/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.Demanda;
import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.service.DemandaService;
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
@RequestMapping("/demanda")
public class DemandaController {

    private static final Logger logger = LoggerFactory.getLogger(DemandaController.class);
    private final DemandaService Service;
    
    @Autowired
    Categorias categorias; 

    @Autowired
    public DemandaController(DemandaService demandaService) {
        this.Service = demandaService;
    }

    // Muestra el formulario para crear una nueva historia clínica
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        // Creamos una nueva instancia para inicializar los datos
        model.addAttribute("demanda", new Demanda());
        model.addAttribute("opcionesParte", categorias.getPartesCuerpo());
        model.addAttribute("opcionesUbicacion", categorias.getUbicaciones());

        return "demanda/formulario";
    }

    // Recibe los datos enviados desde el formulario
    @PostMapping
    //  public String guardarHistoriaClinica(@ModelAttribute ModelAttribute model) {
    public String guardar(@ModelAttribute("demanda") Demanda demanda, Model model) {
        logger.info(demanda.toJson());

        try {
            Service.guardar(demanda);
            model.addAttribute("successMessage", "Demanda guardada exitosamente.");
            return "redirect:/demanda/nuevo"; // Redirigir al listado o una página de éxito
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "demanda/formulario"; // Volver al formulario si hay error
        }
    }

}
