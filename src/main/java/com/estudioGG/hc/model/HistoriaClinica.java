/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoriaClinica implements Identifiable, IComprobantes {

    private String dni;    // Relación con el cliente
    private String fecha;  // Fecha en formato ISO 8601 (YYYY-MM-DD)
    private List<Registro> registros = new ArrayList<>(); // Lista de registros
    private String fechaCarga = LocalDate.now().toString();

    @Override
    public String getId() {
        return dni+fecha; // Usamos el código como identificador
    }

    public HistoriaClinica() {
    }

    // Constructor
    public HistoriaClinica(String codigo, String dni, String fecha, List<Registro> registros) {
        this.dni = dni;
        this.fecha = fecha;
        this.registros = registros;
    }

    // Getters y Setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }
    
    public String toJson()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(HistoriaClinica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @return the fechaCarga
     */
    public String getFechaCarga() {
        return fechaCarga;
    }

    /**
     * @param fechaCarga the fechaCarga to set
     */
    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }
}
