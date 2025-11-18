package com.example.automotora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Comuna;
import com.example.automotora.model.Concesionario;
import com.example.automotora.repository.ComunaRepository;
import com.example.automotora.repository.ConcesionarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ConcesionarioRepository concesionarioRepository;

    public List<Comuna> getAllComunas() {
        return comunaRepository.findAll();
    }

    public Comuna getComunaById(Integer id) {
        return comunaRepository.findById(id).orElse(null);
    }

    public List<Comuna> getComunasByNombre(String nombre) {
        return comunaRepository.findByNombre(nombre);
    }

    public List<Comuna> getComunasByRegionId(Integer regionId) {
        return comunaRepository.findByRegionId(regionId);
    }

    public Comuna saveComuna(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public Comuna updateComuna(Integer id, Comuna comunaActualizada) {
        Comuna comuna = comunaRepository.findById(id).orElse(null);
        if (comuna != null) {
            comuna.setNombre(comunaActualizada.getNombre());
            comuna.setRegion(comunaActualizada.getRegion());
            return comunaRepository.save(comuna);
        }
        return null;
    }

    public Comuna patchComuna(Integer id, Comuna comunaParcial) {
        Comuna comuna = comunaRepository.findById(id).orElse(null);
        if (comuna != null) {
            if (comunaParcial.getNombre() != null) {
                comuna.setNombre(comunaParcial.getNombre());
            }
            if (comunaParcial.getRegion() != null) {
                comuna.setRegion(comunaParcial.getRegion());
            }
            return comunaRepository.save(comuna);
        }
        return null;
    }

    public void deleteComuna(Integer id) {
        List<Concesionario> concesionarios = concesionarioRepository.findByComunaId(id);
        concesionarioRepository.deleteAll(concesionarios);
        comunaRepository.deleteById(id);
    }
}
