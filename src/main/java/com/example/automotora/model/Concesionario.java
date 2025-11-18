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
@Table(name = "concesionario")
public class Concesionario {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreConcesionario", length = 100, nullable = false)
    private String nombre;

    @Column(name = "direccionConcesionario", length = 150, nullable = false)
    private String direccion;

    @Column(name = "telefonoConcesionario", length = 15, nullable = false)
    private String telefono;

    @Column(name = "correoConcesionario", length = 100, nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;
}
