/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.estudioGG.hc.controller;

import com.estudioGG.hc.model.Proceso;
import com.estudioGG.hc.service.HistoryService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo_Avalos
 */
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(HistoryController.class);
    private final HistoryService _service;
    public HistoryController(HistoryService service) {
        this._service = service;
    }

    @GetMapping("/{dni}")
    public ResponseEntity<List<Proceso>> getClientHistory(@PathVariable String dni) {
        logger.info("Criteria:" + dni);
        List<Proceso> processes = _service.getHistory(dni);
        return ResponseEntity.ok(processes);
    }
}
