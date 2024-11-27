package com.educacional.educacional.controller;


import com.educacional.educacional.dto.AlunoRequestDTO;
import com.educacional.educacional.model.Aluno;
import com.educacional.educacional.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = this.repository.findAll();
        if (alunos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno com ID " + id + " não encontrado."));
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setDataNascimento(dto.dataNascimento());

        Aluno savedAluno = this.repository.save(aluno);
        return ResponseEntity.status(201).body(savedAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno com ID " + id + " não encontrado."));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setDataNascimento(dto.dataNascimento());

        Aluno updatedAluno = this.repository.save(aluno);
        return ResponseEntity.ok(updatedAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno com ID " + id + " não encontrado."));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
}
