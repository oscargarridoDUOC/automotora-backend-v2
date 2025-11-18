package com.example.automotora.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "modelo", length = 150, nullable = false)
    private String modelo;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "precio", precision = 13 ,nullable = false)
    private Integer precio;

    @Column(name = "descripcion", length = 1000, nullable = true)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "transmision_id", nullable = false)
    private Transmision transmision;

    @ManyToOne
    @JoinColumn(name = "combustible_id", nullable = false)
    private Combustible combustible;

    @ManyToOne
    @JoinColumn(name = "concesionario_id", nullable = false)
    private Concesionario concesionario;

}
