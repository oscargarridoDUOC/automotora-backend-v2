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

import com.example.automotora.model.Marca;
import com.example.automotora.service.MarcaService;

@RestController
@RequestMapping("/api/v1/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> marcas = marcaService.getAllMarcas();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Integer id) {
        Marca marca = marcaService.getMarcaById(id);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Marca> getMarcaByNombre(@RequestParam String nombre) {
        Optional<Marca> marca = marcaService.getMarcasByNombre(nombre);
        return marca.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) {
        Marca nuevaMarca = marcaService.saveMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        Marca marcaActualizada = marcaService.updateMarca(id, marca);
        if (marcaActualizada != null) {
            return ResponseEntity.ok(marcaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Marca> patchMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        Marca marcaActualizada = marcaService.patchMarca(id, marca);
        if (marcaActualizada != null) {
            return ResponseEntity.ok(marcaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        marcaService.deleteMarca(id);
        return ResponseEntity.noContent().build();
    }
}

