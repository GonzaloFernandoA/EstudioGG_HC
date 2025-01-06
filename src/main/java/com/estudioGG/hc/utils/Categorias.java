/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class Categorias {

    public List<ComboOpcion> getUbicaciones() {
        List<ComboOpcion> opciones = new ArrayList<>();
        opciones.add(new ComboOpcion("-", "-"));
        opciones.add(new ComboOpcion("I", "Izquierdo"));
        opciones.add(new ComboOpcion("D", "Derecho"));
        return opciones;
    }

    public List<ComboOpcion> getPartesCuerpo() {
        List<ComboOpcion> opciones = new ArrayList<>();
        opciones.add(new ComboOpcion("CE", "CERVICAL"));
        opciones.add(new ComboOpcion("LU", "LUMBAR"));
        opciones.add(new ComboOpcion("HO", "HOMBRO"));
        opciones.add(new ComboOpcion("CO", "CODO"));

        opciones.add(new ComboOpcion("MU", "MUÑECA"));
        opciones.add(new ComboOpcion("DO", "DORSAL")); 
        opciones.add(new ComboOpcion("RO", "RODILLA"));         
        opciones.add(new ComboOpcion("FX", "FRACTURA"));           
        opciones.add(new ComboOpcion("TO", "TOBILLO"));          
        
        return opciones;
    }
}
