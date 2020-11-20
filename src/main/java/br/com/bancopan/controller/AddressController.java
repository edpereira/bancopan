package br.com.bancopan.controller;

import br.com.bancopan.domain.Address;
import br.com.bancopan.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/address/{clientCpf}")
    public ResponseEntity addAddress(@PathVariable String clientCpf, @RequestBody Address address) {
        try {
            return ResponseEntity.ok(addressService.createAddress(clientCpf, address));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error not expected", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/address/{clientCpf}/{cep}")
    public ResponseEntity getClientAddressByCEP(@PathVariable String clientCpf, @PathVariable String cep) {
        log.info("getting client address by cpf {} and cep {}", clientCpf, cep);
        return ResponseEntity.ok(addressService.getAddress(clientCpf, cep));
    }

    @GetMapping("/address/{cep}")
    public ResponseEntity getAddressByCEP(@PathVariable String cep) {
        try {
            return ResponseEntity.ok(addressService.getAddress(cep));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/address/{clientCpf}/{cep}")
    public ResponseEntity updateClientAddress(@PathVariable String clientCpf, @PathVariable String cep, @RequestBody Address address) {
        try {
            return ResponseEntity.ok(addressService.updateClientAddress(clientCpf, cep, address));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
