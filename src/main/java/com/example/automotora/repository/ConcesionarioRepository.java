package com.example.automotora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Concesionario;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConcesionarioRepository extends JpaRepository<Concesionario, Integer> {

    List<Concesionario> findByComunaId(Integer comunaId);

    List<Concesionario> findByNombre(String nombre);

    Optional<Concesionario> findByCorreo(String correo);
}