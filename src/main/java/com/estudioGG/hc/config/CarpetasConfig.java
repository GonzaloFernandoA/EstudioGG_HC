/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Gonzalo_Avalos
 */
@Configuration
public class CarpetasConfig {

    @Value("${server.data.cliente}")
    private String cliente;

    @Value("${server.data.categoria}")
    private String categoria;

    @Value("${server.data.histclinica}")
    private String histclinica;

    @Value("${server.data.pericia}")
    private String pericia;

    @Value("${server.data.demanda}")
    private String demanda;

    @Value("${server.data.turno}")
    private String turno;
    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the histclinica
     */
    public String getHistclinica() {
        return histclinica;
    }

    /**
     * @param histclinica the histclinica to set
     */
    public void setHistclinica(String histclinica) {
        this.histclinica = histclinica;
    }

    /**
     * @return the pericia
     */
    public String getPericia() {
        return pericia;
    }

    /**
     * @param pericia the pericia to set
     */
    public void setPericia(String pericia) {
        this.pericia = pericia;
    }

    /**
     * @return the demanda
     */
    public String getDemanda() {
        return demanda;
    }

    /**
     * @param demanda the demanda to set
     */
    public void setDemanda(String demanda) {
        this.demanda = demanda;
    }

    /**
     * @return the turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }
}
