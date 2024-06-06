package com.pi.model;

import com.pi.dto.MatriculaRequest;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
public class Matriculas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMat;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplinas disciplina;

    @Column(name = "dataMatricula")
    private LocalDateTime dataMatricula;

    @Column(name = "valorPago")
    private double valorPago;

    @OneToOne
    @JoinColumn(name = "aluno_id")
    private Pessoas aluno;

    @Column(name = "periodo")
    private int periodo;

    public static Matriculas of(MatriculaRequest matriculaRequest, Pessoas aluno, Disciplinas disciplinas) {
        var matricula = new Matriculas();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplinas);
        matricula.setValorPago(matriculaRequest.getValorPago());
        matricula.setPeriodo(matriculaRequest.getPeriodo());
        matricula.setDataMatricula(LocalDateTime.now());
        return matricula;
    }

    public Matriculas() {

    }

    public Matriculas(int idMat, Disciplinas disciplina, LocalDateTime dataMatricula, double valorPago, Pessoas aluno, int periodo) {
        this.idMat = idMat;
        this.disciplina = disciplina;
        this.dataMatricula = dataMatricula;
        this.valorPago = valorPago;
        this.aluno = aluno;
        this.periodo = periodo;
    }

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

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
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
