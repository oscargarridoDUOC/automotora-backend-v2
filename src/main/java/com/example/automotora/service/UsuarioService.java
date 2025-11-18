package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Usuario;
import com.example.automotora.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Optional<Usuario> getUsuarioByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Optional<Usuario> getUsuarioByRut(String rut) {
        return usuarioRepository.findByRut(rut);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setRut(usuarioActualizado.getRut());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuario.setRol(usuarioActualizado.getRol());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public Usuario patchUsuario(Integer id, Usuario usuarioParcial) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            if (usuarioParcial.getNombre() != null) {
                usuario.setNombre(usuarioParcial.getNombre());
            }
            if (usuarioParcial.getCorreo() != null) {
                usuario.setCorreo(usuarioParcial.getCorreo());
            }
            if (usuarioParcial.getRut() != null) {
                usuario.setRut(usuarioParcial.getRut());
            }
            if (usuarioParcial.getContrasena() != null) {
                usuario.setContrasena(usuarioParcial.getContrasena());
            }
            if (usuarioParcial.getRol() != null) {
                usuario.setRol(usuarioParcial.getRol());
            }
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
