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
        opciones.add(new ComboOpcion("I", "Izquierdo"));
        opciones.add(new ComboOpcion("D", "Derecho"));
        opciones.add(new ComboOpcion("C", "Centro"));
        opciones.add(new ComboOpcion("S", "Superior"));
        opciones.add(new ComboOpcion("N", "Inferior"));
        opciones.add(new ComboOpcion("A", "Adelante"));
        opciones.add(new ComboOpcion("T", "Atras"));
        return opciones;
    }

    public List<ComboOpcion> getPartesCuerpo() {
        List<ComboOpcion> opciones = new ArrayList<>();
        opciones.add(new ComboOpcion("H", "Hombro"));
        opciones.add(new ComboOpcion("P", "Pie"));
        opciones.add(new ComboOpcion("M", "Mano"));
        opciones.add(new ComboOpcion("E", "Pecho"));
        opciones.add(new ComboOpcion("C", "Cabeza"));
        return opciones;
    }
}
