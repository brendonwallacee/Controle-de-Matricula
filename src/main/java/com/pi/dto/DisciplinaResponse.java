package com.pi.dto;

import com.pi.model.Disciplinas;

public class DisciplinaResponse {

    private int id;
    private String nomeDisciplina;
    private int cargaHoraria;
    private PessoaResponse professor;
    private int limiteAlunos;

    public static DisciplinaResponse of(Disciplinas disciplina) {
        return new DisciplinaResponse(
                disciplina.getId(),
                disciplina.getNomeDisciplina(),
                disciplina.getCargaHoraria(),
                PessoaResponse.of(disciplina.getProfessor()),
                disciplina.getLmtAlunos()
        );
    }

    public DisciplinaResponse() {
    }

    public DisciplinaResponse(int id, String nomeDisciplina, int cargaHoraria, PessoaResponse professor, int limiteAlunos) {
        this.id = id;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.limiteAlunos = limiteAlunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public PessoaResponse getProfessor() {
        return professor;
    }

    public void setProfessor(PessoaResponse professor) {
        this.professor = professor;
    }

    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }
}