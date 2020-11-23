package br.com.empresa.controller;

import br.com.empresa.domain.Client;
import br.com.empresa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("/client")
    public ResponseEntity createClient(@RequestBody Client client) {
        try {
            return ResponseEntity.ok(service.createClient(client));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/client/{cpf}")
    public ResponseEntity getClientByCPF(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(service.getClientByCPF(cpf));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
