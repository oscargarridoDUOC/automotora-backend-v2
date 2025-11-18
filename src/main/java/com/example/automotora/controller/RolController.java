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

import com.example.automotora.model.Rol;
import com.example.automotora.service.RolService;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Integer id) {
        Rol rol = rolService.getRolById(id);
        if (rol != null) {
            return ResponseEntity.ok(rol);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<Rol>> getRolesByNombre(@RequestParam String nombre) {
        List<Rol> roles = rolService.getRolesByNombre(nombre);
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.saveRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Integer id, @RequestBody Rol rol) {
        Rol rolActualizado = rolService.updateRol(id, rol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rol> patchRol(@PathVariable Integer id, @RequestBody Rol rol) {
        Rol rolActualizado = rolService.patchRol(id, rol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}

