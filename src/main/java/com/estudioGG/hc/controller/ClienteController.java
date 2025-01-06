/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.repository.S3RepositoryImpl;
import com.estudioGG.hc.service.ClienteService;
import com.estudioGG.hc.service.HistoryService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private ClienteService _service;
    private HistoryService _historyService;

    @Autowired
    public ClienteController(ClienteService service, HistoryService historyService) {
        _service = service;
        _historyService = historyService;
    }

    @GetMapping("/formulario")
    public String mostrar(Model model) {
        model.addAttribute("cliente", new Cliente()); // Se pasa un objeto vacío para el formulario
        return "clientes/formulario";
    }

    @GetMapping("/listado")
    public String listar(Model model) {
        List<Cliente> clientes = _service.obtenerTodos();

        model.addAttribute("clientes", clientes);
        return "clientes/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {

        _service.guardar(cliente);
        return "redirect:/clientes/formulario"; // Redirigir a una página de éxito o a otra ruta
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<Cliente> buscarCliente(@RequestParam String dni) {

        Cliente cliente = _service.obtener(dni);
        if (cliente != null) {
            logger.info("Dni: " + dni);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente) {
        
        logger.info("Controler: " + cliente.toJson());
      
      //  _service.guardar(cliente);
        return ResponseEntity.ok("Cliente actualizado correctamente");
    }
    
    
}
