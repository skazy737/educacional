package com.educacional.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    @JsonIgnore
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    @JsonIgnore
    private Turma turma;

    // Removido o mapeamento para a lista de 'notas' que foi removida do novo código.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}
