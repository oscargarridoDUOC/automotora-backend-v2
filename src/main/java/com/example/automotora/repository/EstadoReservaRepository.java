package com.example.automotora.repository;

import com.example.automotora.model.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoReservaRepository extends JpaRepository<EstadoReserva, Integer> {

    Optional<EstadoReserva> findByEstado(String estado);
}