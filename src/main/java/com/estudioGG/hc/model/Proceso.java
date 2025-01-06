/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.model;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Proceso {
    private HistoriaClinica historia; 
    private Demanda demanda; 
    private EstudiosPerito estudios; 
    private Pericia pericia; 

    /**
     * @return the historia
     */
    public HistoriaClinica getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(HistoriaClinica historia) {
        this.historia = historia;
    }

    /**
     * @return the demanda
     */
    public Demanda getDemanda() {
        return demanda;
    }

    /**
     * @param demanda the demanda to set
     */
    public void setDemanda(Demanda demanda) {
        this.demanda = demanda;
    }

    /**
     * @return the estudios
     */
    public EstudiosPerito getEstudios() {
        return estudios;
    }

    /**
     * @param estudios the estudios to set
     */
    public void setEstudios(EstudiosPerito estudios) {
        this.estudios = estudios;
    }

    /**
     * @return the pericia
     */
    public Pericia getPericia() {
        return pericia;
    }

    /**
     * @param pericia the pericia to set
     */
    public void setPericia(Pericia pericia) {
        this.pericia = pericia;
    }
}
