package com.educacional.educacional.repository;

import com.educacional.educacional.model.Curso;
import com.educacional.educacional.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

    List<Turma> findByCurso(Curso curso);

}

