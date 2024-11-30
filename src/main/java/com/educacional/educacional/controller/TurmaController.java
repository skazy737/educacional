package com.educacional.educacional.controller;

import com.educacional.educacional.dto.TurmaRequestDTO;
import com.educacional.educacional.model.Turma;
import com.educacional.educacional.model.Curso;  // Importe o modelo Curso
import com.educacional.educacional.repository.TurmaRepository;
import com.educacional.educacional.repository.CursoRepository;  // Importe o repositório de Curso
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
@Validated
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping()
    public ResponseEntity<List<Turma>> findAll() {
        List<Turma> turmas = this.turmaRepository.findAll();
        if (turmas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> save(@Valid @RequestBody TurmaRequestDTO dto) {
        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + dto.curso_id()));

        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        Turma savedTurma = this.turmaRepository.save(turma);
        return ResponseEntity.status(201).body(savedTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @Valid @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + dto.curso_id()));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        Turma updatedTurma = this.turmaRepository.save(turma);
        return ResponseEntity.ok(updatedTurma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.turmaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada com ID: " + id));

        this.turmaRepository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
