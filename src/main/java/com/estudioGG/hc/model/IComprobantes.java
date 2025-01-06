/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.estudioGG.hc.model;

import java.util.List;

/**
 *
 * @author Gonzalo_Avalos
 */
public interface IComprobantes {
    public String getDni();
    public String getFecha();
    public List<Registro> getRegistros();
}
