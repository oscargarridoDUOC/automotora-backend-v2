package com.example.automotora.repository;

import com.example.automotora.model.EstadoReserva;
import com.example.automotora.model.Reserva;
import com.example.automotora.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByUsuarioId(Integer usuarioId);

    List<Reserva> findByVehiculoId(Integer vehiculoId);

    List<Reserva> findByEstadoId(Integer estadoId);

    void deleteByUsuarioId(Integer usuarioId);

    void deleteByVehiculoId(Integer vehiculoId);

    void deleteByEstadoId(Integer estadoId);

    boolean existsByVehiculoAndEstado(Vehiculo vehiculo, EstadoReserva estado);
}