package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Combustible;
import com.example.automotora.model.Vehiculo;
import com.example.automotora.repository.CombustibleRepository;
import com.example.automotora.repository.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CombustibleService {

    @Autowired
    private CombustibleRepository combustibleRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Combustible> getAllCombustibles() {
        return combustibleRepository.findAll();
    }

    public Combustible getCombustibleById(Integer id) {
        return combustibleRepository.findById(id).orElse(null);
    }

    public Optional<Combustible> getCombustibleByTipo(String tipo) {
        return combustibleRepository.findByTipo(tipo);
    }

    public Combustible saveCombustible(Combustible combustible) {
        return combustibleRepository.save(combustible);
    }

    public Combustible updateCombustible(Integer id, Combustible combustibleActualizado) {
        Combustible combustible = combustibleRepository.findById(id).orElse(null);
        if (combustible != null) {
            combustible.setTipo(combustibleActualizado.getTipo());
            return combustibleRepository.save(combustible);
        }
        return null;
    }

    public Combustible patchCombustible(Integer id, Combustible combustibleParcial) {
        Combustible combustible = combustibleRepository.findById(id).orElse(null);
        if (combustible != null) {
            if (combustibleParcial.getTipo() != null) {
                combustible.setTipo(combustibleParcial.getTipo());
            }
            return combustibleRepository.save(combustible);
        }
        return null;
    }

    public void deleteCombustible(Integer id) {
        List<Vehiculo> vehiculos = vehiculoRepository.findByCombustibleId(id);
        vehiculoRepository.deleteAll(vehiculos);
        combustibleRepository.deleteById(id);
    }
}
