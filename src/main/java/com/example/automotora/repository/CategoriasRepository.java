package com.example.automotora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

    List<Categorias> findByCategoriaId(Integer categoriaId);

    List<Categorias> findByVehiculoId(Integer vehiculoId);

    void deleteByCategoriaId(Integer categoriaId);

    void deleteByVehiculoId(Integer vehiculoId);
}
