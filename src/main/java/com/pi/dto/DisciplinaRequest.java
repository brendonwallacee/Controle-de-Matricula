package com.pi.dto;

import com.pi.model.Disciplinas;
import com.pi.model.Pessoas;

public class DisciplinaRequest {
    private int id;
    private String nomeDisciplina;
    private int cargaHoraria;
    private int professorId;
    private int limiteAlunos;

    public DisciplinaRequest() {
    }

    public DisciplinaRequest(int id, String nomeDisciplina, int cargaHoraria, int professorId, int limiteAlunos) {
        this.id= id;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professorId = professorId;
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

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }
}
