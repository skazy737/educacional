package com.educacional.educacional.controller;

import com.educacional.educacional.dto.NotaRequestDTO;
import com.educacional.educacional.model.Nota;
import com.educacional.educacional.repository.NotaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/notas")
public class NotaController {

    private final NotaRepository repository;

    @Autowired
    public NotaController(NotaRepository notaRepository) {
        this.repository = notaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        List<Nota> notas = this.repository.findAll();
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public Nota save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento());
        return this.repository.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.dataLancamento());
        return this.repository.save(nota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        this.repository.delete(nota);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obter boletim de um aluno", description = "Retorna o curso e as notas do aluno informado pelo ID.")
    @GetMapping("/boletim/{alunoId}")
    public ResponseEntity<List<Map<String, Object>>> getBoletimAluno(@PathVariable Integer alunoId) {
        List<Map<String, Object>> boletim = repository.obterBoletimPorAluno(alunoId);
        return ResponseEntity.ok(boletim);
    }

    @Operation(summary = "Obter notas por turma", description = "Retorna as notas dos alunos de uma turma específica pelo ID da turma.")
    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Map<String, Object>>> getNotasPorTurma(@PathVariable Integer turmaId) {
        List<Map<String, Object>> notas = repository.obterNotasPorTurma(turmaId);
        return ResponseEntity.ok(notas);
    }

    @Operation(summary = "Obter notas por disciplina", description = "Retorna as notas dos alunos em uma disciplina específica pelo ID da disciplina.")
    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Map<String, Object>>> getNotasPorDisciplina(@PathVariable Integer disciplinaId) {
        List<Map<String, Object>> notas = repository.listarNotasPorDisciplina(disciplinaId);
        return ResponseEntity.ok(notas);
    }

    @Operation(summary = "Obter média das notas por curso", description = "Retorna a média das notas dos alunos agrupadas por curso.")
    @GetMapping("/media/curso")
    public ResponseEntity<List<Map<String, Object>>> getMediaNotasPorCurso() {
        List<Map<String, Object>> medias = repository.calcularMediaPorCurso();
        return ResponseEntity.ok(medias);
    }

    @Operation(summary = "Obter média das notas por aluno", description = "Retorna a média das notas dos alunos agrupadas por aluno.")
    @GetMapping("/media/aluno")
    public ResponseEntity<List<Map<String, Object>>> getMediaNotasPorAluno() {
        List<Map<String, Object>> mediaAluno = repository.calcularMediaPorAluno();
        return ResponseEntity.ok(mediaAluno);
    }
}
