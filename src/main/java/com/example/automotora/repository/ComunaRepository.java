package com.example.automotora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Comuna;
import java.util.List;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    List<Comuna> findByNombre(String nombre);

    List<Comuna> findByRegionId(Integer regionId);
}