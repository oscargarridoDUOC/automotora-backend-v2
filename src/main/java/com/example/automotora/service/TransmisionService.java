package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Transmision;
import com.example.automotora.model.Vehiculo;
import com.example.automotora.repository.TransmisionRepository;
import com.example.automotora.repository.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransmisionService {

    @Autowired
    private TransmisionRepository transmisionRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Transmision> getAllTransmisiones() {
        return transmisionRepository.findAll();
    }

    public Transmision getTransmisionById(Integer id) {
        return transmisionRepository.findById(id).orElse(null);
    }

    public Optional<Transmision> getTransmisionByTipo(String tipo) {
        return transmisionRepository.findByTipo(tipo);
    }

    public Transmision saveTransmision(Transmision transmision) {
        return transmisionRepository.save(transmision);
    }

    public Transmision updateTransmision(Integer id, Transmision transmisionActualizada) {
        Transmision transmision = transmisionRepository.findById(id).orElse(null);
        if (transmision != null) {
            transmision.setTipo(transmisionActualizada.getTipo());
            return transmisionRepository.save(transmision);
        }
        return null;
    }

    public Transmision patchTransmision(Integer id, Transmision transmisionParcial) {
        Transmision transmision = transmisionRepository.findById(id).orElse(null);
        if (transmision != null) {
            if (transmisionParcial.getTipo() != null) {
                transmision.setTipo(transmisionParcial.getTipo());
            }
            return transmisionRepository.save(transmision);
        }
        return null;
    }

    public void deleteTransmision(Integer id) {
        List<Vehiculo> vehiculos = vehiculoRepository.findByTransmisionId(id);
        vehiculoRepository.deleteAll(vehiculos);
        transmisionRepository.deleteById(id);
    }
}
