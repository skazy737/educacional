package com.educacional.educacional.repository;

import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
