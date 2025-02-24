/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Identifiable {

    private String apellido;
    private String nombre;
    private String centro;
    private String dni;
    private String companias ;
    
    
    @JsonIgnore
    private List<Proceso> historia; 

    @Override
    public String getId() {
        return dni; // Usamos el código como identificador
    }
    
    public Cliente()
    {
    }
    // Constructor
    public Cliente(String apellido, String nombre, String companias, String dni, String centro) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.companias = companias;
        this.centro = centro; 
        this.dni = dni;
        this.historia = null;
    }

    // Getters y Setters
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the historia
     */
    @JsonIgnore
    public List<Proceso> getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(List<Proceso> historia) {
        this.historia = historia;
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
     * @return the centro
     */
    public String getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * @return the companias
     */
    public String getCompanias() {
        return companias;
    }

    /**
     * @param companias the companias to set
     */
    public void setCompanias(String companias) {
        this.companias = companias;
    }
    
}
