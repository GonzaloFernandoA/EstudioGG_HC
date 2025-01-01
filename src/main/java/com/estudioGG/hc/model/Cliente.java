/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.model;

public class Cliente implements Identifiable {

    private String apellido;
    private String nombre;
    private String telefono;
    private String dni;

    @Override
    public String getId() {
        return dni; // Usamos el código como identificador
    }
    
    public Cliente()
    {
    }
    // Constructor
    public Cliente(String apellido, String nombre, String telefono, String dni) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
