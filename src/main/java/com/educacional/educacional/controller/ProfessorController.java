package com.educacional.educacional.controller;

import com.educacional.educacional.dto.ProfessorRequestDTO;
import com.educacional.educacional.model.Professor;
import com.educacional.educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@RestController
@RequestMapping("api/professores")
@Validated
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping()
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professores = this.repository.findAll();
        if (professores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + id)); // Mensagem de erro detalhada
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> save(@Valid @RequestBody ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        Professor savedProfessor = this.repository.save(professor);
        return ResponseEntity.status(201).body(savedProfessor);
    }

    // Endpoint para atualizar um professor existente
    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Integer id, @Valid @RequestBody ProfessorRequestDTO dto) { // Validação aplicada ao DTO
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + id)); // Mensagem de erro detalhada

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        Professor updatedProfessor = this.repository.save(professor);
        return ResponseEntity.ok(updatedProfessor);
    }

    // Endpoint para deletar um professor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com ID: " + id)); // Mensagem de erro detalhada

        this.repository.delete(professor);
        return ResponseEntity.noContent().build(); // Retorna noContent após deletar
    }
}
