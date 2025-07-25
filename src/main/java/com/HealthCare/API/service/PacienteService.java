package com.HealthCare.API.service;

import com.HealthCare.API.dto.PacienteDTO;
import com.HealthCare.API.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    //Interface para metodos de modulo pacientes

    List<Paciente> listarTodos();
    Optional<Paciente> obtenerPorDocumento(String documento);
    Paciente guardar(PacienteDTO dto);
    Paciente actualizar(String documento, PacienteDTO dto);
    void eliminar(String documento);
}
