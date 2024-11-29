package com.educacional.educacional.controller;

import com.educacional.educacional.dto.DisciplinaRequestDTO;
import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Disciplina;
import com.educacional.educacional.model.Nota;
import com.educacional.educacional.model.Professor;
import com.educacional.educacional.repository.CursoRepository;
import com.educacional.educacional.repository.DisciplinaRepository;
import com.educacional.educacional.repository.NotaRepository;
import com.educacional.educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping()
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = this.repository.findAll();
        if (disciplinas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + id));
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@Valid @RequestBody DisciplinaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + dto.curso_id()));

        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + dto.professor_id()));


        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = this.repository.save(disciplina);
        return ResponseEntity.status(201).body(savedDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @Valid @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + id));

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + dto.curso_id()));

        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + dto.professor_id()));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        Disciplina updatedDisciplina = this.repository.save(disciplina);
        return ResponseEntity.ok(updatedDisciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + id));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-nota")
    public ResponseEntity<Disciplina> addNota(@PathVariable Integer id,
                                              @Valid @RequestBody Nota nota) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + id));

        nota.setDisciplina(disciplina);
        this.notaRepository.save(nota);

        return ResponseEntity.ok(disciplina);
    }
}
