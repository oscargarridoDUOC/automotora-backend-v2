package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Concesionario;
import com.example.automotora.model.Vehiculo;
import com.example.automotora.repository.ConcesionarioRepository;
import com.example.automotora.repository.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConcesionarioService {

    @Autowired
    private ConcesionarioRepository concesionarioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Concesionario> getAllConcesionarios() {
        return concesionarioRepository.findAll();
    }

    public Concesionario getConcesionarioById(Integer id) {
        return concesionarioRepository.findById(id).orElse(null);
    }

    public List<Concesionario> getConcesionariosByComunaId(Integer comunaId) {
        return concesionarioRepository.findByComunaId(comunaId);
    }

    public List<Concesionario> getConcesionariosByNombre(String nombre) {
        return concesionarioRepository.findByNombre(nombre);
    }

    public Optional<Concesionario> getConcesionarioByCorreo(String correo) {
        return concesionarioRepository.findByCorreo(correo);
    }

    public Concesionario saveConcesionario(Concesionario concesionario) {
        return concesionarioRepository.save(concesionario);
    }

    public Concesionario updateConcesionario(Integer id, Concesionario concesionarioActualizado) {
        Concesionario concesionario = concesionarioRepository.findById(id).orElse(null);
        if (concesionario != null) {
            concesionario.setNombre(concesionarioActualizado.getNombre());
            concesionario.setDireccion(concesionarioActualizado.getDireccion());
            concesionario.setTelefono(concesionarioActualizado.getTelefono());
            concesionario.setCorreo(concesionarioActualizado.getCorreo());
            concesionario.setComuna(concesionarioActualizado.getComuna());
            return concesionarioRepository.save(concesionario);
        }
        return null;
    }

    public Concesionario patchConcesionario(Integer id, Concesionario concesionarioParcial) {
        Concesionario concesionario = concesionarioRepository.findById(id).orElse(null);
        if (concesionario != null) {
            if (concesionarioParcial.getNombre() != null) {
                concesionario.setNombre(concesionarioParcial.getNombre());
            }
            if (concesionarioParcial.getDireccion() != null) {
                concesionario.setDireccion(concesionarioParcial.getDireccion());
            }
            if (concesionarioParcial.getTelefono() != null) {
                concesionario.setTelefono(concesionarioParcial.getTelefono());
            }
            if (concesionarioParcial.getCorreo() != null) {
                concesionario.setCorreo(concesionarioParcial.getCorreo());
            }
            if (concesionarioParcial.getComuna() != null) {
                concesionario.setComuna(concesionarioParcial.getComuna());
            }
            return concesionarioRepository.save(concesionario);
        }
        return null;
    }

    public void deleteConcesionario(Integer id) {
        List<Vehiculo> vehiculos = vehiculoRepository.findByConcesionarioId(id);
        vehiculoRepository.deleteAll(vehiculos);
        concesionarioRepository.deleteById(id);
    }
}
