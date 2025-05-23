/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.model;


/**
 *
 * @author Gonzalo_Avalos
 */
public class Turno extends Jasonable implements Identifiable  {
    private String dni;
    private String telefono;
    private String dia;
    private String hora;
    private String clinica;
    private String direccion;
    private String sector;
    private String apellido;
    private String nombre;
    
    private String recordaTresDias;
    private String recordaUnDia;
    private String confirmacion;
    
    private Cliente cliente = new Cliente();
    
    /**
     * @return the apellido
     */
       
    public Turno()
    {
        
    }
    
    public Turno(String dni, String telefono, String dia, String hora, String clinica, String direccion, String sector, String recordaTresDias, String recordaUnDia, String confirmacion) {
        this.dni = dni;
        this.telefono = telefono;
        this.dia = dia;
        this.hora = hora;
        this.clinica = clinica;
        this.direccion = direccion;
        this.sector = sector;
        
        this.recordaTresDias = recordaTresDias;
        this.recordaUnDia = recordaUnDia;
        this.confirmacion = confirmacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre )
    {
        this.nombre = nombre; 
    }
    
    public String getAppellido()
    {
        return cliente.getApellido();
    }

    
    @Override
    public String getId() {
        return dni; // Usamos el código como identificador
    }
    
    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the clinica
     */
    public String getClinica() {
        return clinica;
    }

    /**
     * @param clinica the clinica to set
     */
    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getRecordaTresDias() {
        return this.recordaTresDias;
    }
    
    public void setRecordaTresDias(String recordaTresDias) {
        this.recordaTresDias = recordaTresDias;
    }
    
    public String getRecordaUnDia() {
        return this.recordaUnDia;
    }
    
    public void setRecordaUnDia(String recordaUnDia) {
        this.recordaUnDia = recordaUnDia;
    }
    
    public String getConfirmacion() {
        return confirmacion;
    }
    
    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
}
