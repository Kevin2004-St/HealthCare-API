package com.HealthCare.API.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;

    private String apellidos;

    private String especialidad;

    private String numeroRegistro;
}
