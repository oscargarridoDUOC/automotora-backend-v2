package com.example.automotora.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "correoUsuario", length= 100, nullable = false, unique = true)
    private String correo;

    @Column(name = "rutUsuario", length = 12, nullable = false, unique = true)
    private String rut;

    @Column(name = "contrasenaUsuario", length = 255, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
