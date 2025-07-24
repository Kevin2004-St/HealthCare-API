package com.HealthCare.API.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "medicos")

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;

    private String apellidos;

    private String especialidad;

    private String numeroRegistro;
}
