package com.educacional.educacional.repository;

import com.educacional.educacional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

    @Query("SELECT new map(c.nome as nomeCurso, d.nome as nomeDisciplina, n.nota as valorNota, n.dataLancamento as dataNota) " +
            "FROM Nota n " +
            "JOIN n.matricula m " +
            "JOIN m.turma t " +
            "JOIN t.curso c " +
            "JOIN n.disciplina d " +
            "WHERE m.aluno.id = :idAluno")
    List<Map<String, Object>> obterBoletimPorAluno(@Param("idAluno") Integer alunoId);

    @Query("SELECT new map(a.nome as nomeAluno, d.nome as nomeDisciplina, n.nota as valorNota, n.dataLancamento as dataNota) " +
            "FROM Nota n " +
            "JOIN n.matricula m " +
            "JOIN m.aluno a " +
            "JOIN n.disciplina d " +
            "WHERE m.turma.id = :idTurma")
    List<Map<String, Object>> obterNotasPorTurma(@Param("idTurma") Integer turmaId);

    @Query("SELECT new map(a.nome as nomeAluno, n.nota as valorNota, n.dataLancamento as dataNota) " +
            "FROM Nota n " +
            "JOIN n.matricula m " +
            "JOIN m.aluno a " +
            "WHERE n.disciplina.id = :idDisciplina")
    List<Map<String, Object>> listarNotasPorDisciplina(@Param("idDisciplina") Integer disciplinaId);

    @Query("SELECT new map(c.nome as nomeCurso, AVG(n.nota) as mediaNotas) " +
            "FROM Nota n " +
            "JOIN n.matricula m " +
            "JOIN m.turma t " +
            "JOIN t.curso c " +
            "GROUP BY c.nome")
    List<Map<String, Object>> calcularMediaPorCurso();

    @Query("SELECT new map(a.nome as nomeAluno, AVG(n.nota) as mediaNotas) " +
            "FROM Nota n " +
            "JOIN n.matricula m " +
            "JOIN m.aluno a " +
            "JOIN n.disciplina d " +
            "GROUP BY a.nome")
    List<Map<String, Object>> calcularMediaPorAluno();
}
