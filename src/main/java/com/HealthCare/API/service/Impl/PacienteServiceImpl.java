package com.HealthCare.API.service.Impl;

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
    public Paciente guardar(Paciente paciente) {
       if(pacienteRepository.existsByDocumento(paciente.getDocumento())){
           throw new RuntimeException("Ya existe un paciente con ese documento");
       }
       return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizar(Long id, Paciente pacienteActualizado) {
        return pacienteRepository.findById(id).map(p -> {
            p.setNombres(pacienteActualizado.getNombres());
            p.setApellidos(pacienteActualizado.getApellidos());
            p.setDocumento(pacienteActualizado.getDocumento());
            p.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
            p.setGenero(pacienteActualizado.getGenero());
            p.setDireccion(pacienteActualizado.getDireccion());
            p.setCelular(pacienteActualizado.getCelular());
            return  pacienteRepository.save(p);
        }).orElseThrow(()-> new RuntimeException("Paciente no encontrado con ID "+ id));
    }

    @Override
    public void eliminar(String documento) {
        Paciente paciente = pacienteRepository.findByDocumento(documento)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        pacienteRepository.delete(paciente);

    }
}
