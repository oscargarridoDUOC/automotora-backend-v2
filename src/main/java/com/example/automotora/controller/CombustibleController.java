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

import com.example.automotora.model.Combustible;
import com.example.automotora.service.CombustibleService;

@RestController
@RequestMapping("/api/v1/combustibles")
public class CombustibleController {

    @Autowired
    private CombustibleService combustibleService;

    @GetMapping
    public ResponseEntity<List<Combustible>> getAllCombustibles() {
        List<Combustible> combustibles = combustibleService.getAllCombustibles();
        return ResponseEntity.ok(combustibles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Combustible> getCombustibleById(@PathVariable Integer id) {
        Combustible combustible = combustibleService.getCombustibleById(id);
        if (combustible != null) {
            return ResponseEntity.ok(combustible);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Combustible> getCombustibleByTipo(@RequestParam String tipo) {
        Optional<Combustible> combustible = combustibleService.getCombustibleByTipo(tipo);
        return combustible.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Combustible> createCombustible(@RequestBody Combustible combustible) {
        Combustible nuevoCombustible = combustibleService.saveCombustible(combustible);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCombustible);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Combustible> updateCombustible(@PathVariable Integer id, @RequestBody Combustible combustible) {
        Combustible combustibleActualizado = combustibleService.updateCombustible(id, combustible);
        if (combustibleActualizado != null) {
            return ResponseEntity.ok(combustibleActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Combustible> patchCombustible(@PathVariable Integer id, @RequestBody Combustible combustible) {
        Combustible combustibleActualizado = combustibleService.patchCombustible(id, combustible);
        if (combustibleActualizado != null) {
            return ResponseEntity.ok(combustibleActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCombustible(@PathVariable Integer id) {
        combustibleService.deleteCombustible(id);
        return ResponseEntity.noContent().build();
    }
}

