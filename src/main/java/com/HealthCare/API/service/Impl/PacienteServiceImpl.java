package com.HealthCare.API.service.Impl;

import com.HealthCare.API.dto.PacienteDTO;
import com.HealthCare.API.entity.Paciente;
import com.HealthCare.API.repository.PacienteRepository;
import com.HealthCare.API.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public  PacienteServiceImpl(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> obtenerPorDocumento(String documento) {
        return pacienteRepository.findByDocumento(documento);
    }

    @Override
    public Paciente guardar(PacienteDTO dto) {

        Paciente paciente = new Paciente();
        paciente.setDocumento(dto.getDocumento());
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setEmail(dto.getEmail());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setGenero(dto.getGenero());
        paciente.setDireccion(dto.getDireccion());
        paciente.setCelular(dto.getCelular());

        if(pacienteRepository.existsByDocumento(paciente.getDocumento())){
            throw new RuntimeException("Ya existe un paciente con ese documento");
        }

        return  pacienteRepository.save(paciente);

    }

    @Override
    public Paciente actualizar(String documento, PacienteDTO dto) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findByDocumento(documento);

        if(pacienteOpt.isEmpty()){
            throw new EntityNotFoundException("Paciente con documento "+ " no encontrado");
        }

        Paciente paciente = pacienteOpt.get();
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setEmail(dto.getEmail());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setGenero(dto.getGenero());
        paciente.setDireccion(dto.getDireccion());
        paciente.setCelular(dto.getCelular());

        return pacienteRepository.save(paciente);

    }

    @Override
    public void eliminar(String documento) {
        Paciente paciente = pacienteRepository.findByDocumento(documento)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        pacienteRepository.delete(paciente);

    }
}
