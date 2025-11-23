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

import com.example.automotora.model.Contacto;
import com.example.automotora.service.ContactoService;

@RestController
@RequestMapping("/api/v1/contacto")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping
    public ResponseEntity<List<Contacto>> getAllContactos() {
        List<Contacto> contactos = contactoService.getAllContactos();
        return ResponseEntity.ok(contactos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> getContactoById(@PathVariable Integer id) {
        Contacto contacto = contactoService.getContactoById(id);
        if (contacto != null) {
            return ResponseEntity.ok(contacto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Contacto> createContacto(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoService.saveContacto(contacto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoContacto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contacto> updateContacto(@PathVariable Integer id, @RequestBody Contacto contacto) {
        Contacto contactoActualizado = contactoService.updateContacto(id, contacto);
        if (contactoActualizado != null) {
            return ResponseEntity.ok(contactoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Contacto> patchContacto(@PathVariable Integer id, @RequestBody Contacto contacto) {
        Contacto contactoActualizado = contactoService.patchContacto(id, contacto);
        if (contactoActualizado != null) {
            return ResponseEntity.ok(contactoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContacto(@PathVariable Integer id) {
        contactoService.deleteContacto(id);
        return ResponseEntity.noContent().build();
    }
}
