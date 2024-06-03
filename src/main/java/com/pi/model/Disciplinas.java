package com.pi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplinas")
public class Disciplinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomeDisciplina")
    private String nomeDisciplina;

    @Column(name = "cargaHoraria")
    private int cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Pessoas professor;

    @Column(name = "limiteAlunos")
    private int lmtAlunos;

    public static Disciplinas of(Disciplinas request) {
        var disciplina = new Disciplinas();
        disciplina.setNomeDisciplina(request.getNomeDisciplina());
        disciplina.setCargaHoraria(request.getCargaHoraria());
        disciplina.setProfessor(new Pessoas(request.getProfessor().getId()));
        disciplina.setLmtAlunos(request.getLmtAlunos());
        return disciplina;
    }

    public Disciplinas() {

    }

    public Disciplinas(int id) {
    this.id = id;
    }

    public Disciplinas(int id, String nomeDisciplina, int cargaHoraria, Pessoas professor, int lmtAlunos) {
        this.id = id;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.lmtAlunos = lmtAlunos;
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

    public Pessoas getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoas professor) {
        this.professor = professor;
    }

    public int getLmtAlunos() {
        return lmtAlunos;
    }

    public void setLmtAlunos(int lmtAlunos) {
        this.lmtAlunos = lmtAlunos;
    }


}

