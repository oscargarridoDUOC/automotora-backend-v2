package com.example.automotora.repository;

import com.example.automotora.model.EstadoReserva; // <-- Importante
import com.example.automotora.model.Reserva;
import com.example.automotora.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByUsuarioId(Integer usuarioId);

    List<Reserva> findByVehiculoId(Integer vehiculoId);

    boolean existsByVehiculoAndEstado(Vehiculo vehiculo, EstadoReserva estado);
}