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

import com.example.automotora.model.Concesionario;
import com.example.automotora.service.ConcesionarioService;

@RestController
@RequestMapping("/api/v1/concesionarios")
public class ConcesionarioController {

    @Autowired
    private ConcesionarioService concesionarioService;

    @GetMapping
    public ResponseEntity<List<Concesionario>> getAllConcesionarios() {
        List<Concesionario> concesionarios = concesionarioService.getAllConcesionarios();
        return ResponseEntity.ok(concesionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concesionario> getConcesionarioById(@PathVariable Integer id) {
        Concesionario concesionario = concesionarioService.getConcesionarioById(id);
        if (concesionario != null) {
            return ResponseEntity.ok(concesionario);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/comuna/{comunaId}")
    public ResponseEntity<List<Concesionario>> getConcesionariosByComunaId(@PathVariable Integer comunaId) {
        List<Concesionario> concesionarios = concesionarioService.getConcesionariosByComunaId(comunaId);
        return ResponseEntity.ok(concesionarios);
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<Concesionario>> getConcesionariosByNombre(@RequestParam String nombre) {
        List<Concesionario> concesionarios = concesionarioService.getConcesionariosByNombre(nombre);
        return ResponseEntity.ok(concesionarios);
    }

    @GetMapping("/correo")
    public ResponseEntity<Concesionario> getConcesionarioByCorreo(@RequestParam String correo) {
        Optional<Concesionario> concesionario = concesionarioService.getConcesionarioByCorreo(correo);
        return concesionario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Concesionario> createConcesionario(@RequestBody Concesionario concesionario) {
        Concesionario nuevoConcesionario = concesionarioService.saveConcesionario(concesionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConcesionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Concesionario> updateConcesionario(@PathVariable Integer id, @RequestBody Concesionario concesionario) {
        Concesionario concesionarioActualizado = concesionarioService.updateConcesionario(id, concesionario);
        if (concesionarioActualizado != null) {
            return ResponseEntity.ok(concesionarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Concesionario> patchConcesionario(@PathVariable Integer id, @RequestBody Concesionario concesionario) {
        Concesionario concesionarioActualizado = concesionarioService.patchConcesionario(id, concesionario);
        if (concesionarioActualizado != null) {
            return ResponseEntity.ok(concesionarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcesionario(@PathVariable Integer id) {
        concesionarioService.deleteConcesionario(id);
        return ResponseEntity.noContent().build();
    }
}

