package com.example.automotora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Marca;
import com.example.automotora.model.Vehiculo;
import com.example.automotora.model.Reserva;
import com.example.automotora.model.Categorias;
import com.example.automotora.repository.MarcaRepository;
import com.example.automotora.repository.VehiculoRepository;
import com.example.automotora.repository.ReservaRepository;
import com.example.automotora.repository.CategoriasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Marca getMarcaById(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Optional<Marca> getMarcasByNombre(String nombre) {
        return marcaRepository.findByNombre(nombre);
    }

    public Marca saveMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca updateMarca(Integer id, Marca marcaActualizada) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            marca.setNombre(marcaActualizada.getNombre());
            return marcaRepository.save(marca);
        }
        return null;
    }

    public Marca patchMarca(Integer id, Marca marcaParcial) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            if (marcaParcial.getNombre() != null) {
                marca.setNombre(marcaParcial.getNombre());
            }
            return marcaRepository.save(marca);
        }
        return null;
    }

    public void deleteMarca(Integer id) {
        List<Vehiculo> vehiculos = vehiculoRepository.findByMarcaId(id);

        for (Vehiculo vehiculo : vehiculos) {
            List<Reserva> reservas = reservaRepository.findByVehiculoId(vehiculo.getId());
            reservaRepository.deleteAll(reservas);

            List<Categorias> categorias = categoriasRepository.findByVehiculoId(vehiculo.getId());
            categoriasRepository.deleteAll(categorias);
        }

        vehiculoRepository.deleteAll(vehiculos);
        marcaRepository.deleteById(id);
    }
}
