package com.example.automotora.controller;

import java.util.List;

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

import com.example.automotora.model.Comuna;
import com.example.automotora.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> getAllComunas() {
        List<Comuna> comunas = comunaService.getAllComunas();
        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getComunaById(@PathVariable Integer id) {
        Comuna comuna = comunaService.getComunaById(id);
        if (comuna != null) {
            return ResponseEntity.ok(comuna);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<Comuna>> getComunasByNombre(@RequestParam String nombre) {
        List<Comuna> comunas = comunaService.getComunasByNombre(nombre);
        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<Comuna>> getComunasByRegionId(@PathVariable Integer regionId) {
        List<Comuna> comunas = comunaService.getComunasByRegionId(regionId);
        return ResponseEntity.ok(comunas);
    }

    @PostMapping
    public ResponseEntity<Comuna> createComuna(@RequestBody Comuna comuna) {
        Comuna nuevaComuna = comunaService.saveComuna(comuna);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaComuna);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> updateComuna(@PathVariable Integer id, @RequestBody Comuna comuna) {
        Comuna comunaActualizada = comunaService.updateComuna(id, comuna);
        if (comunaActualizada != null) {
            return ResponseEntity.ok(comunaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> patchComuna(@PathVariable Integer id, @RequestBody Comuna comuna) {
        Comuna comunaActualizada = comunaService.patchComuna(id, comuna);
        if (comunaActualizada != null) {
            return ResponseEntity.ok(comunaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComuna(@PathVariable Integer id) {
        comunaService.deleteComuna(id);
        return ResponseEntity.noContent().build();
    }
}

