package com.example.automotora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.Vehiculo;
import com.example.automotora.model.Reserva;
import com.example.automotora.model.Categorias;
import com.example.automotora.repository.VehiculoRepository;
import com.example.automotora.repository.ReservaRepository;
import com.example.automotora.repository.CategoriasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    public List<Vehiculo> getVehiculosByMarcaId(Integer marcaId) {
        return vehiculoRepository.findByMarcaId(marcaId);
    }

    public List<Vehiculo> getVehiculosByConcesionarioId(Integer concesionarioId) {
        return vehiculoRepository.findByConcesionarioId(concesionarioId);
    }

    public List<Vehiculo> getVehiculosByTransmisionId(Integer transmisionId) {
        return vehiculoRepository.findByTransmisionId(transmisionId);
    }

    public List<Vehiculo> getVehiculosByCombustibleId(Integer combustibleId) {
        return vehiculoRepository.findByCombustibleId(combustibleId);
    }

    public List<Vehiculo> getVehiculosByModelo(String modelo) {
        return vehiculoRepository.findByModelo(modelo);
    }

    public List<Vehiculo> getVehiculosByAnio(Integer anio) {
        return vehiculoRepository.findByAnio(anio);
    }

    public List<Vehiculo> getVehiculosByMarcaNombre(String nombreMarca) {
        return vehiculoRepository.findByMarcaNombre(nombreMarca);
    }

    public List<Vehiculo> getVehiculosByConcesionarioNombre(String nombreConcesionario) {
        return vehiculoRepository.findByConcesionarioNombre(nombreConcesionario);
    }

    public List<Vehiculo> getVehiculosByTransmisionTipo(String tipoTransmision) {
        return vehiculoRepository.findByTransmisionTipo(tipoTransmision);
    }

    public List<Vehiculo> getVehiculosByCombustibleTipo(String tipoCombustible) {
        return vehiculoRepository.findByCombustibleTipo(tipoCombustible);
    }

    public List<Vehiculo> getVehiculosMasNuevosQue(Integer anio) {
        return vehiculoRepository.buscarVehiculosMasNuevosQue(anio);
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculoActualizado) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo != null) {
            vehiculo.setModelo(vehiculoActualizado.getModelo());
            vehiculo.setAnio(vehiculoActualizado.getAnio());
            vehiculo.setPrecio(vehiculoActualizado.getPrecio());
            vehiculo.setDescripcion(vehiculoActualizado.getDescripcion());
            vehiculo.setImagen(vehiculoActualizado.getImagen());
            vehiculo.setMarca(vehiculoActualizado.getMarca());
            vehiculo.setTransmision(vehiculoActualizado.getTransmision());
            vehiculo.setCombustible(vehiculoActualizado.getCombustible());
            vehiculo.setConcesionario(vehiculoActualizado.getConcesionario());
            return vehiculoRepository.save(vehiculo);
        }
        return null;
    }

    public Vehiculo patchVehiculo(Integer id, Vehiculo vehiculoParcial) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo != null) {
            if (vehiculoParcial.getModelo() != null) {
                vehiculo.setModelo(vehiculoParcial.getModelo());
            }
            if (vehiculoParcial.getAnio() != null) {
                vehiculo.setAnio(vehiculoParcial.getAnio());
            }
            if (vehiculoParcial.getPrecio() != null) {
                vehiculo.setPrecio(vehiculoParcial.getPrecio());
            }
            if (vehiculoParcial.getDescripcion() != null) {
                vehiculo.setDescripcion(vehiculoParcial.getDescripcion());
            }
            if (vehiculoParcial.getImagen() != null) {
                vehiculo.setImagen(vehiculoParcial.getImagen());
            }
            if (vehiculoParcial.getMarca() != null) {
                vehiculo.setMarca(vehiculoParcial.getMarca());
            }
            if (vehiculoParcial.getTransmision() != null) {
                vehiculo.setTransmision(vehiculoParcial.getTransmision());
            }
            if (vehiculoParcial.getCombustible() != null) {
                vehiculo.setCombustible(vehiculoParcial.getCombustible());
            }
            if (vehiculoParcial.getConcesionario() != null) {
                vehiculo.setConcesionario(vehiculoParcial.getConcesionario());
            }
            return vehiculoRepository.save(vehiculo);
        }
        return null;
    }

    public void deleteVehiculo(Integer id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);

        if (vehiculo != null) {
            List<Reserva> reservas = reservaRepository.findByVehiculoId(id);
            for (Reserva reserva : reservas) {
                reservaRepository.delete(reserva);
            }

            List<Categorias> categorias = categoriasRepository.findByVehiculoId(id);
            for (Categorias categoria : categorias) {
                categoriasRepository.delete(categoria);
            }

            vehiculoRepository.delete(vehiculo);
        }
    }
}
