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
    private String companias;
    private String telefono;
    private String turno;
    private String hora;
    private String clinica;
    private String direccion;
    private String sector;
    
    
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
    public Cliente(String apellido, String nombre, String telefono, String companias, String dni, String centro, String turno, String hora, String clinica, String direccion, String sector) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.companias = companias;
        this.centro = centro; 
        this.dni = dni;
        this.historia = null;
        this.telefono = telefono;
        this.turno = turno;
        this.hora = hora;
        this.clinica = clinica;
        this.direccion = direccion;
        this.sector = sector;
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
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getTurno() {
        return turno;
    }
    
    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    public String getHora() {
        return hora;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public String getClinica() {
        return clinica;
    }
    
    public void setClinica(String clinica) {
        this.clinica = clinica;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getSector() {
        return sector;
    }
    
    public void setSector(String sector) {
        this.sector = sector;
    }
    
}
