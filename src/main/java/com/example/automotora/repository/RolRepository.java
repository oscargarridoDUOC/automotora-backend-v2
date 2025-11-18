package com.example.automotora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    List<Rol> findByNombre(String nombre);
}
