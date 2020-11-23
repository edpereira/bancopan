package br.com.empresa.controller;

import br.com.empresa.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

    @Autowired
    private StateService service;

    @GetMapping("/estados")
    public ResponseEntity getStates() {
        try {
            return ResponseEntity.ok(service.getOrderedStates());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    @GetMapping("/estados/{siglaEstado}/municipios")
    public ResponseEntity getCities(@PathVariable String siglaEstado) {
        try {
            return ResponseEntity.ok(service.getCities(siglaEstado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
