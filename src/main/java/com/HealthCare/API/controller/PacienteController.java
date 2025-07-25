package com.HealthCare.API.controller;

import com.HealthCare.API.dto.PacienteDTO;
import com.HealthCare.API.entity.Paciente;
import com.HealthCare.API.service.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService =pacienteService;
    }

    //metodo para retornar todos los pacientes
    @GetMapping
    public List<Paciente> listarTodos(){
        return pacienteService.listarTodos();
    }

    @GetMapping("/{documento}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable String documento){
        return pacienteService.obtenerPorDocumento(documento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PacienteDTO dto){

        Paciente pacienteGuardado = pacienteService.guardar(dto);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Paciente creado exitosamente");
        respuesta.put("paciente: ", pacienteGuardado);

        return ResponseEntity.ok(respuesta);

    }

    @PutMapping("/{documento}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable String documento, @Valid @RequestBody PacienteDTO dto) {
        try {
            Paciente pacienteActualizado = pacienteService.actualizar(documento, dto);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Paciente actualizado exitosamente");
            respuesta.put("paciente", pacienteActualizado);

            return ResponseEntity.ok(respuesta);

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        }
    }


    @DeleteMapping("/{documento}")
    public ResponseEntity<String> eliminar(@PathVariable String documento){
        pacienteService.eliminar(documento);
        return ResponseEntity.ok("Paciente eliminado correctamente");
    }

}
