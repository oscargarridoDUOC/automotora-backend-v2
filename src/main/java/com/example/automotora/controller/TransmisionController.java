package com.example.automotora.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.automotora.model.Transmision;
import com.example.automotora.service.TransmisionService;

@RestController
@RequestMapping("/api/v1/transmisiones")
public class TransmisionController {

    @Autowired
    private TransmisionService transmisionService;

    @GetMapping
    public ResponseEntity<List<Transmision>> getAllTransmisiones() {
        List<Transmision> transmisiones = transmisionService.getAllTransmisiones();
        return ResponseEntity.ok(transmisiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transmision> getTransmisionById(@PathVariable Integer id) {
        Transmision transmision = transmisionService.getTransmisionById(id);
        if (transmision != null) {
            return ResponseEntity.ok(transmision);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Transmision> getTransmisionByTipo(@RequestParam String tipo) {
        Optional<Transmision> transmision = transmisionService.getTransmisionByTipo(tipo);
        return transmision.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transmision> createTransmision(@RequestBody Transmision transmision) {
        Transmision nuevaTransmision = transmisionService.saveTransmision(transmision);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTransmision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transmision> updateTransmision(@PathVariable Integer id, @RequestBody Transmision transmision) {
        Transmision transmisionActualizada = transmisionService.updateTransmision(id, transmision);
        if (transmisionActualizada != null) {
            return ResponseEntity.ok(transmisionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Transmision> patchTransmision(@PathVariable Integer id, @RequestBody Transmision transmision) {
        Transmision transmisionActualizada = transmisionService.patchTransmision(id, transmision);
        if (transmisionActualizada != null) {
            return ResponseEntity.ok(transmisionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransmision(@PathVariable Integer id) {
        transmisionService.deleteTransmision(id);
        return ResponseEntity.noContent().build();
    }
}

