package com.pi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matriculas")
public class Matriculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMat;

    @ManyToOne
    @JoinColumn(name = "disciplina")
    private Disciplinas disciplina;

    @Column(name = "dataMatricula")
    private int dataMatricula;

    @Column(name = "valorPago")
    private double valorPago;

    @OneToOne
    @JoinColumn(name = "aluno")
    private Pessoas aluno;

    @Column(name = "periodo")
    private int periodo;

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    public Disciplinas getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplinas disciplina) {
        this.disciplina = disciplina;
    }

    public int getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(int dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Pessoas getAluno() {
        return aluno;
    }

    public void setAluno(Pessoas aluno) {
        this.aluno = aluno;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }


}
