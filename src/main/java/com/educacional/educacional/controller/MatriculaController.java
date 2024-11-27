package com.educacional.educacional.controller;

import com.educacional.educacional.dto.MatriculaRequestDTO;
import com.educacional.educacional.dto.NotaRequestDTO;
import com.educacional.educacional.model.Matricula;
import com.educacional.educacional.model.Nota;
import com.educacional.educacional.repository.MatriculaRepository;
import com.educacional.educacional.repository.NotaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@RestController
@RequestMapping("api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping()
    public ResponseEntity<List<Matricula>> findAll() {
        List<Matricula> matriculas = this.repository.findAll();
        if (matriculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada com ID: " + id));
        return ResponseEntity.ok(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada com ID: " + id));

        this.repository.delete(matricula);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-nota")
    public ResponseEntity<Matricula> addNota(@PathVariable Integer id,
                                             @Valid @RequestBody NotaRequestDTO dto) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada com ID: " + id));

        Nota nota = new Nota();
        nota.setMatricula(matricula);
        nota.setNota(dto.nota());

        this.notaRepository.save(nota);

        return ResponseEntity.ok(matricula);
    }
}
