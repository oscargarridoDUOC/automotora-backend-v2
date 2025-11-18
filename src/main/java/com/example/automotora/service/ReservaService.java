package com.example.automotora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.EstadoReserva;
import com.example.automotora.model.Reserva;
import com.example.automotora.model.Vehiculo;
import com.example.automotora.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva getReservaById(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public List<Reserva> getReservasByUsuarioId(Integer usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> getReservasByVehiculoId(Integer vehiculoId) {
        return reservaRepository.findByVehiculoId(vehiculoId);
    }

    public boolean existeReservaActivaPorVehiculo(Vehiculo vehiculo, EstadoReserva estado) {
        return reservaRepository.existsByVehiculoAndEstado(vehiculo, estado);
    }

    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Integer id, Reserva reservaActualizada) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            reserva.setUsuario(reservaActualizada.getUsuario());
            reserva.setVehiculo(reservaActualizada.getVehiculo());
            reserva.setEstado(reservaActualizada.getEstado());
            reserva.setFechaReserva(reservaActualizada.getFechaReserva());
            reserva.setFechaEntrega(reservaActualizada.getFechaEntrega());
            reserva.setPrecioReserva(reservaActualizada.getPrecioReserva());
            return reservaRepository.save(reserva);
        }
        return null;
    }

    public Reserva patchReserva(Integer id, Reserva reservaParcial) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            if (reservaParcial.getUsuario() != null) {
                reserva.setUsuario(reservaParcial.getUsuario());
            }
            if (reservaParcial.getVehiculo() != null) {
                reserva.setVehiculo(reservaParcial.getVehiculo());
            }
            if (reservaParcial.getEstado() != null) {
                reserva.setEstado(reservaParcial.getEstado());
            }
            if (reservaParcial.getFechaReserva() != null) {
                reserva.setFechaReserva(reservaParcial.getFechaReserva());
            }
            if (reservaParcial.getFechaEntrega() != null) {
                reserva.setFechaEntrega(reservaParcial.getFechaEntrega());
            }
            if (reservaParcial.getPrecioReserva() != null) {
                reserva.setPrecioReserva(reservaParcial.getPrecioReserva());
            }
            return reservaRepository.save(reserva);
        }
        return null;
    }

    public void deleteReserva(Integer id) {
        reservaRepository.deleteById(id);
    }
}

