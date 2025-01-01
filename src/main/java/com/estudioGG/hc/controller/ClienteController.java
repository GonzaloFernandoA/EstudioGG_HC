/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.Cliente;
import com.estudioGG.hc.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService _service;

    @Autowired
    public ClienteController(ClienteService service) {
        _service = service;
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
    
    /*  @PostMapping("/guardar")
    public String guardarCliente(@RequestParam String apellido,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String telefono,
            @RequestParam String dni) {
        // Crear y guardar la entidad Cliente
        Cliente cliente = new Cliente();
        cliente.setApellido(apellido);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setDni(dni);
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {

        _service.guardar(cliente);
        // Aquí puedes llamar a un servicio para guardar el cliente
        return "redirect:/clientes/formulario"; // Redirigir a una página de éxito o a otra ruta
    }
}
