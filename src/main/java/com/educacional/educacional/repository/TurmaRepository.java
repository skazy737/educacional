package com.educacional.educacional.repository;

import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
}
