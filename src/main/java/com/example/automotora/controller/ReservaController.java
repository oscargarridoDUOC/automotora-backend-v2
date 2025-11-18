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
import org.springframework.web.bind.annotation.RestController;

import com.example.automotora.model.Reserva;
import com.example.automotora.service.ReservaService;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Integer id) {
        Reserva reserva = reservaService.getReservaById(id);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> getReservasByUsuarioId(@PathVariable Integer usuarioId) {
        List<Reserva> reservas = reservaService.getReservasByUsuarioId(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<Reserva>> getReservasByVehiculoId(@PathVariable Integer vehiculoId) {
        List<Reserva> reservas = reservaService.getReservasByVehiculoId(vehiculoId);
        return ResponseEntity.ok(reservas);
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.saveReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        Reserva reservaActualizada = reservaService.updateReserva(id, reserva);
        if (reservaActualizada != null) {
            return ResponseEntity.ok(reservaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Reserva> patchReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        Reserva reservaActualizada = reservaService.patchReserva(id, reserva);
        if (reservaActualizada != null) {
            return ResponseEntity.ok(reservaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Integer id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}

