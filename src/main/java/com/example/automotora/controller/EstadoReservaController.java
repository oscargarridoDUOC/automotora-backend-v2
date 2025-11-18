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

import com.example.automotora.model.EstadoReserva;
import com.example.automotora.service.EstadoReservaService;

@RestController
@RequestMapping("/api/v1/estados-reserva")
public class EstadoReservaController {

    @Autowired
    private EstadoReservaService estadoReservaService;

    @GetMapping
    public ResponseEntity<List<EstadoReserva>> getAllEstadosReserva() {
        List<EstadoReserva> estadosReserva = estadoReservaService.getAllEstadosReserva();
        return ResponseEntity.ok(estadosReserva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoReserva> getEstadoReservaById(@PathVariable Integer id) {
        EstadoReserva estadoReserva = estadoReservaService.getEstadoReservaById(id);
        if (estadoReserva != null) {
            return ResponseEntity.ok(estadoReserva);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/estado")
    public ResponseEntity<EstadoReserva> getEstadoReservaByEstado(@RequestParam String estado) {
        EstadoReserva estadoReserva = estadoReservaService.getEstadoReservaByEstado(estado);
        if (estadoReserva != null) {
            return ResponseEntity.ok(estadoReserva);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EstadoReserva> createEstadoReserva(@RequestBody EstadoReserva estadoReserva) {
        EstadoReserva nuevoEstadoReserva = estadoReservaService.saveEstadoReserva(estadoReserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstadoReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoReserva> updateEstadoReserva(@PathVariable Integer id, @RequestBody EstadoReserva estadoReserva) {
        EstadoReserva estadoReservaActualizado = estadoReservaService.updateEstadoReserva(id, estadoReserva);
        if (estadoReservaActualizado != null) {
            return ResponseEntity.ok(estadoReservaActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstadoReserva> patchEstadoReserva(@PathVariable Integer id, @RequestBody EstadoReserva estadoReserva) {
        EstadoReserva estadoReservaActualizado = estadoReservaService.patchEstadoReserva(id, estadoReserva);
        if (estadoReservaActualizado != null) {
            return ResponseEntity.ok(estadoReservaActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadoReserva(@PathVariable Integer id) {
        estadoReservaService.deleteEstadoReserva(id);
        return ResponseEntity.noContent().build();
    }
}

