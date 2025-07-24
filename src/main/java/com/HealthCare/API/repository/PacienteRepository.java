package com.HealthCare.API.repository;

import com.HealthCare.API.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    //Metodo para comprobar la existencia de un paciente
    boolean existsByDocumento(String documento);

    Optional<Paciente> findByDocumento(String documento);


}
