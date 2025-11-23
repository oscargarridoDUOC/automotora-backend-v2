package com.example.automotora.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Contacto;
import com.example.automotora.repository.ContactoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    public List<Contacto> getAllContactos() {
        return contactoRepository.findAll();
    }

    public Contacto getContactoById(Integer id) {
        return contactoRepository.findById(id).orElse(null);
    }

    public Contacto saveContacto(Contacto contacto) {
        contacto.setFechaCreacion(Instant.now());
        return contactoRepository.save(contacto);
    }

    public Contacto updateContacto(Integer id, Contacto contactoActualizado) {
        Contacto contacto = contactoRepository.findById(id).orElse(null);
        if (contacto != null) {
            contacto.setNombre(contactoActualizado.getNombre());
            contacto.setEmail(contactoActualizado.getEmail());
            contacto.setTelefono(contactoActualizado.getTelefono());
            contacto.setMensaje(contactoActualizado.getMensaje());
            return contactoRepository.save(contacto);
        }
        return null;
    }

    public Contacto patchContacto(Integer id, Contacto contactoParcial) {
        Contacto contacto = contactoRepository.findById(id).orElse(null);
        if (contacto != null) {
            if (contactoParcial.getNombre() != null) {
                contacto.setNombre(contactoParcial.getNombre());
            }
            if (contactoParcial.getEmail() != null) {
                contacto.setEmail(contactoParcial.getEmail());
            }
            if (contactoParcial.getTelefono() != null) {
                contacto.setTelefono(contactoParcial.getTelefono());
            }
            if (contactoParcial.getMensaje() != null) {
                contacto.setMensaje(contactoParcial.getMensaje());
            }
            return contactoRepository.save(contacto);
        }
        return null;
    }

    public void deleteContacto(Integer id) {
        contactoRepository.deleteById(id);
    }
}
