/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.model.Pericia;
import com.estudioGG.hc.model.Turno;
import com.estudioGG.hc.service.ClienteService;
import com.estudioGG.hc.service.HistoryService;
import com.estudioGG.hc.service.TurnoService;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Gonzalo_Avalos
 */
@Controller
@RequestMapping("/turnos")
public class TurnoController {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TurnoController.class);

    @Autowired
    private TurnoService _service;
    
    @GetMapping("/formulario")
    public String mostrar(Model model) {
        model.addAttribute("turno", _service.nuevo());
        return "turnos/formulario";
    }

    @GetMapping("/listado")
    public String listar(Model model) {
        List<Turno> turnos = _service.obtenerTodos();
        
        model.addAttribute("turnos", turnos);
        return "turnos/listado";
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarTurno(@RequestBody Map<String, String> payload) {
        logger.info("Eliminando turno por DNI: {}", payload.get("dni"));
        String dni = payload.get("dni");
        // Call your service to delete the record by DNI
        boolean eliminado = _service.eliminarPorDni(dni);
        if (eliminado) {
            logger.info("Turno con DNI {} eliminado correctamente", dni);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("No se encontró un turno con DNI {}", dni);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Turno turno) {
        logger.info("Guardando entidad...:" + turno.toJson());
        _service.guardar(turno);
        logger.info("Entidad guardada.");
        return "redirect:/turnos/formulario"; // Redirigir a una página de éxito o a otra ruta
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("turno", _service.nuevo());
        
        return "turnos/formulario";
    }

}
