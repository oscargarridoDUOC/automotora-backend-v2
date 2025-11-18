package com.example.automotora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Rol;
import com.example.automotora.model.Usuario;
import com.example.automotora.repository.RolRepository;
import com.example.automotora.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Rol getRolById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    public List<Rol> getRolesByNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol updateRol(Integer id, Rol rolActualizado) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol != null) {
            rol.setNombre(rolActualizado.getNombre());
            return rolRepository.save(rol);
        }
        return null;
    }

    public Rol patchRol(Integer id, Rol rolParcial) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol != null) {
            if (rolParcial.getNombre() != null) {
                rol.setNombre(rolParcial.getNombre());
            }
            return rolRepository.save(rol);
        }
        return null;
    }

    public void deleteRol(Integer id) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        usuarios.stream()
            .filter(usuario -> usuario.getRol() != null && usuario.getRol().getId().equals(id))
            .forEach(usuarioRepository::delete);
        rolRepository.deleteById(id);
    }
}
