/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.HistoriaClinica;
import com.estudioGG.hc.service.HistoriaClinicaService;
import com.estudioGG.hc.utils.Categorias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/historia-clinica")
public class HistoriaClinicaController {

    private static final Logger logger = LoggerFactory.getLogger(HistoriaClinicaController.class);
    private final HistoriaClinicaService historiaClinicaService;
    
    @Autowired
    Categorias categorias; 

    @Autowired
    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    // Muestra el formulario para crear una nueva historia clínica
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute("historiaClinica", new HistoriaClinica());
        model.addAttribute("opcionesParte", categorias.getPartesCuerpo());
        model.addAttribute("opcionesUbicacion", categorias.getUbicaciones());

        return "historia-clinica/formulario";
    }

    // Recibe los datos enviados desde el formulario
    @PostMapping
    //  public String guardarHistoriaClinica(@ModelAttribute ModelAttribute model) {
    public String guardarHistoriaClinica(@ModelAttribute("historiaClinica") HistoriaClinica historiaClinica, Model model) {
        logger.info(historiaClinica.toJson());

        try {
            historiaClinicaService.guardar(historiaClinica);
            model.addAttribute("successMessage", "Historia clínica guardada exitosamente.");
            return "redirect:/historia-clinica/nuevo"; // Redirigir al listado o una página de éxito
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "historia-clinica/formulario"; // Volver al formulario si hay error
        }
    }

}
