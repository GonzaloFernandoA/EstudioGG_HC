/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.model;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Registro {

    private String codigoParteCuerpo; // Código de parte del cuerpo (3 letras)
    private String codigoUbicacion;  // Código de ubicación (3 letras)

     public Registro() {

    }
    
    // Constructor
    public Registro(String codigoParteCuerpo, String codigoUbicacion) {
        this.codigoParteCuerpo = codigoParteCuerpo;
        this.codigoUbicacion = codigoUbicacion;
    }

    // Getters y Setters
    public String getCodigoParteCuerpo() {
        return codigoParteCuerpo;
    }

    public void setCodigoParteCuerpo(String codigoParteCuerpo) {
        this.codigoParteCuerpo = codigoParteCuerpo;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }
    
        @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Registro registro = (Registro) obj;

        if (!codigoParteCuerpo.equals(registro.codigoParteCuerpo)) return false;
        return codigoUbicacion.equals(registro.codigoUbicacion);
    }

    @Override
    public int hashCode() {
        int result = codigoParteCuerpo.hashCode();
        result = 31 * result + codigoUbicacion.hashCode();
        return result;
    }
}

