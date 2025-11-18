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

import com.example.automotora.model.Vehiculo;
import com.example.automotora.service.VehiculoService;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByMarcaId(@PathVariable Integer marcaId) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByMarcaId(marcaId);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/concesionario/{concesionarioId}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByConcesionarioId(@PathVariable Integer concesionarioId) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByConcesionarioId(concesionarioId);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/transmision/{transmisionId}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByTransmisionId(@PathVariable Integer transmisionId) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByTransmisionId(transmisionId);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/combustible/{combustibleId}")
    public ResponseEntity<List<Vehiculo>> getVehiculosByCombustibleId(@PathVariable Integer combustibleId) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByCombustibleId(combustibleId);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/modelo")
    public ResponseEntity<List<Vehiculo>> getVehiculosByModelo(@RequestParam String modelo) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByModelo(modelo);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/anio")
    public ResponseEntity<List<Vehiculo>> getVehiculosByAnio(@RequestParam Integer anio) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByAnio(anio);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/marca/nombre")
    public ResponseEntity<List<Vehiculo>> getVehiculosByMarcaNombre(@RequestParam String nombreMarca) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByMarcaNombre(nombreMarca);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/concesionario/nombre")
    public ResponseEntity<List<Vehiculo>> getVehiculosByConcesionarioNombre(@RequestParam String nombreConcesionario) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByConcesionarioNombre(nombreConcesionario);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/transmision/tipo")
    public ResponseEntity<List<Vehiculo>> getVehiculosByTransmisionTipo(@RequestParam String tipoTransmision) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByTransmisionTipo(tipoTransmision);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/combustible/tipo")
    public ResponseEntity<List<Vehiculo>> getVehiculosByCombustibleTipo(@RequestParam String tipoCombustible) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosByCombustibleTipo(tipoCombustible);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/mas-nuevos")
    public ResponseEntity<List<Vehiculo>> getVehiculosMasNuevosQue(@RequestParam Integer anio) {
        List<Vehiculo> vehiculos = vehiculoService.getVehiculosMasNuevosQue(anio);
        return ResponseEntity.ok(vehiculos);
    }

    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.saveVehiculo(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoActualizado = vehiculoService.updateVehiculo(id, vehiculo);
        if (vehiculoActualizado != null) {
            return ResponseEntity.ok(vehiculoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehiculo> patchVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoActualizado = vehiculoService.patchVehiculo(id, vehiculo);
        if (vehiculoActualizado != null) {
            return ResponseEntity.ok(vehiculoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Integer id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}

