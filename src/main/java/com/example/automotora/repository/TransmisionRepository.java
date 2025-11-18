package com.example.automotora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Transmision;
import java.util.Optional;

@Repository
public interface TransmisionRepository extends JpaRepository<Transmision, Integer> {

    Optional<Transmision> findByTipo(String tipo);
}