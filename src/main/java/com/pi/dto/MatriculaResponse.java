package com.pi.dto;

import com.pi.model.Matriculas;

import java.time.LocalDateTime;

public class MatriculaResponse {

    private int idMat;
    private DisciplinaResponse disciplina;
    private LocalDateTime dataMatricula;
    private double valorPago;
    private PessoaResponse aluno;
    private int periodo;

    public static MatriculaResponse of(Matriculas matricula) {
        return new MatriculaResponse(
                matricula.getIdMat(),
                DisciplinaResponse.of(matricula.getDisciplina()),
                matricula.getDataMatricula(),
                matricula.getValorPago(),
                PessoaResponse.of(matricula.getAluno()),
                matricula.getPeriodo()
        );
    }

    public MatriculaResponse() {
    }

    public MatriculaResponse(int idMat, DisciplinaResponse disciplina, LocalDateTime dataMatricula, double valorPago, PessoaResponse aluno, int periodo) {
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

    public DisciplinaResponse getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaResponse disciplina) {
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

    public PessoaResponse getAluno() {
        return aluno;
    }

    public void setAluno(PessoaResponse aluno) {
        this.aluno = aluno;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}
