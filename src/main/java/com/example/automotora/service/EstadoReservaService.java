package com.example.automotora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.automotora.model.EstadoReserva;
import com.example.automotora.model.Reserva;
import com.example.automotora.repository.EstadoReservaRepository;
import com.example.automotora.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoReservaService {

    @Autowired
    private EstadoReservaRepository estadoReservaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public List<EstadoReserva> getAllEstadosReserva() {
        return estadoReservaRepository.findAll();
    }

    public EstadoReserva getEstadoReservaById(Integer id) {
        return estadoReservaRepository.findById(id).orElse(null);
    }

    public EstadoReserva getEstadoReservaByEstado(String estado) {
        return estadoReservaRepository.findByEstado(estado).orElse(null);
    }

    public EstadoReserva saveEstadoReserva(EstadoReserva estadoReserva) {
        return estadoReservaRepository.save(estadoReserva);
    }

    public EstadoReserva updateEstadoReserva(Integer id, EstadoReserva estadoReservaActualizado) {
        EstadoReserva estadoReserva = estadoReservaRepository.findById(id).orElse(null);
        if (estadoReserva != null) {
            estadoReserva.setEstado(estadoReservaActualizado.getEstado());
            return estadoReservaRepository.save(estadoReserva);
        }
        return null;
    }

    public EstadoReserva patchEstadoReserva(Integer id, EstadoReserva estadoReservaParcial) {
        EstadoReserva estadoReserva = estadoReservaRepository.findById(id).orElse(null);
        if (estadoReserva != null) {
            if (estadoReservaParcial.getEstado() != null) {
                estadoReserva.setEstado(estadoReservaParcial.getEstado());
            }
            return estadoReservaRepository.save(estadoReserva);
        }
        return null;
    }

    public void deleteEstadoReserva(Integer id) {
        List<Reserva> reservas = reservaRepository.findAll();
        reservas.stream()
            .filter(reserva -> reserva.getEstado() != null && reserva.getEstado().getId().equals(id))
            .forEach(reservaRepository::delete);
        estadoReservaRepository.deleteById(id);
    }
}

