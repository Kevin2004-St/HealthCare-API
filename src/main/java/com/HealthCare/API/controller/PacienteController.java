package com.HealthCare.API.controller;

import com.HealthCare.API.entity.Paciente;
import com.HealthCare.API.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Paciente> crear(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.actualizar(id, paciente));
    }

    @DeleteMapping("/{documento}")
    public ResponseEntity<String> eliminar(@PathVariable String documento){
        pacienteService.eliminar(documento);
        return ResponseEntity.ok("Paciente eliminado correctamente");
    }

}
