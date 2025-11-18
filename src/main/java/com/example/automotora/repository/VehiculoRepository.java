package com.example.automotora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.automotora.model.Vehiculo;
import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    List<Vehiculo> findByMarcaId(Integer marcaId);

    List<Vehiculo> findByConcesionarioId(Integer concesionarioId);

    List<Vehiculo> findByTransmisionId(Integer transmisionId);

    List<Vehiculo> findByCombustibleId(Integer combustibleId);

    List<Vehiculo> findByModelo(String modelo);

    List<Vehiculo> findByAnio(Integer anio);

    List<Vehiculo> findByMarcaNombre(String nombreMarca);

    List<Vehiculo> findByConcesionarioNombre(String nombreConcesionario);

    List<Vehiculo> findByTransmisionTipo(String tipoTransmision);

    List<Vehiculo> findByCombustibleTipo(String tipoCombustible);
    
    @Query("SELECT v FROM Vehiculo v WHERE v.anio > :anioBuscado")
    List<Vehiculo> buscarVehiculosMasNuevosQue(
        @Param("anioBuscado") Integer anio
    );
}