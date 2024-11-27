package com.educacional.educacional.controller;

import com.educacional.educacional.dto.CursoRequestDTO;
import com.educacional.educacional.model.Curso;
import com.educacional.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = this.repository.findAll();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCargaHoraria(dto.cargaHoraria());

        Curso savedCurso = this.repository.save(curso);
        return ResponseEntity.status(201).body(savedCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCargaHoraria(dto.cargaHoraria());

        Curso updatedCurso = this.repository.save(curso);
        return ResponseEntity.ok(updatedCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
