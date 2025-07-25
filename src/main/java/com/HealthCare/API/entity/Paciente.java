package com.HealthCare.API.entity;

import com.HealthCare.API.enums.Genero;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "pacientes")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;

    private String nombres;

    private String apellidos;

    private String email;

    private LocalDate fechaNacimiento;

    //Enum String en la base de datos
    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String direccion;

    private String celular;

    public Paciente(){

    }

    public Paciente(Long id, String documento, String nombres, String apellidos, LocalDate fechaNacimiento, Genero genero, String direccion, String celular, String email) {
        this.id = id;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.celular = celular;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
