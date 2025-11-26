package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Usuario;
import com.example.automotora.model.Reserva;
import com.example.automotora.repository.UsuarioRepository;
import com.example.automotora.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReservaRepository reservaRepository;

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
        String passwordHasheada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(passwordHasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setRut(usuarioActualizado.getRut());
            if (usuarioActualizado.getContrasena() != null) {
                String passwordHasheada = passwordEncoder.encode(usuarioActualizado.getContrasena());
                usuario.setContrasena(passwordHasheada);
            }
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
                String passwordHasheada = passwordEncoder.encode(usuarioParcial.getContrasena());
                usuario.setContrasena(passwordHasheada);
            }
            if (usuarioParcial.getRol() != null) {
                usuario.setRol(usuarioParcial.getRol());
            }
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deleteUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            List<Reserva> reservas = reservaRepository.findByUsuarioId(id);
            for (Reserva reserva : reservas) {
                reservaRepository.delete(reserva);
            }

            usuarioRepository.delete(usuario);
        }
    }

    public Usuario login(Usuario usuario) {
        Optional<Usuario> foundUsuarioOpt = usuarioRepository.findByCorreo(usuario.getCorreo());
        Usuario foundUsuario = foundUsuarioOpt.orElse(null);
        if (foundUsuario != null && passwordEncoder.matches(usuario.getContrasena(), foundUsuario.getContrasena())) {
            return foundUsuario;
        }

        return null;
    }

}
