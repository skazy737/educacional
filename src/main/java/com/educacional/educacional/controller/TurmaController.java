package com.educacional.educacional.controller;

import com.educacional.educacional.dto.TurmaRequestDTO;
import com.educacional.educacional.model.Turma;
import com.educacional.educacional.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
@Validated
public class TurmaController {

    @Autowired
    private TurmaRepository repository;


    @GetMapping()
    public ResponseEntity<List<Turma>> findAll() {
        List<Turma> turmas = this.repository.findAll();
        if (turmas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> save(@Valid @RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Turma savedTurma = this.repository.save(turma);
        return ResponseEntity.status(201).body(savedTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @Valid @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Turma updatedTurma = this.repository.save(turma);
        return ResponseEntity.ok(updatedTurma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));

        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
