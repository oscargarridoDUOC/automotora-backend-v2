package com.example.automotora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Region;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    Optional<Region> findByNombre(String nombre);
}