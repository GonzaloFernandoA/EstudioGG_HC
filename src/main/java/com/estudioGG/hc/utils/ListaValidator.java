/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.utils;

import com.estudioGG.hc.model.Registro;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class ListaValidator {

    public boolean validarListasIgualesSinOrden(List<Registro> lista1, List<Registro> lista2) {
        if (lista1 == null || lista2 == null) {
            throw new IllegalArgumentException("Las listas no pueden ser nulas");
        }

        // Convertimos las listas en conjuntos para eliminar duplicados y comparar sin orden
        Set<Registro> conjunto1 = new HashSet<>(lista1);
        Set<Registro> conjunto2 = new HashSet<>(lista2);

        return conjunto1.equals(conjunto2);
    }
}
