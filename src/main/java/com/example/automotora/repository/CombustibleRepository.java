package com.example.automotora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Combustible;
import java.util.Optional;

@Repository
public interface CombustibleRepository extends JpaRepository<Combustible, Integer> {

    Optional<Combustible> findByTipo(String tipo);
}