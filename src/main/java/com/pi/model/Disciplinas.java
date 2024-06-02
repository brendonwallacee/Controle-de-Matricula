package com.pi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "disciplinas")
public class Disciplinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nomeDisciplina")
    private String nomeDisciplina;

    @Column(name = "cargaHoraria")
    private int cargaHoraria;

    @OneToOne
    @JoinColumn(name = "professor")
    private Pessoas professor;

    @Column(name = "limiteAlunos")
    private int lmtAlunos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

