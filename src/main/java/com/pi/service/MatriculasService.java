package com.pi.service;

import com.pi.dto.MatriculaRequest;
import com.pi.dto.MatriculaResponse;
import com.pi.exceptions.exception.ValidacaoException;
import com.pi.model.Disciplinas;
import com.pi.model.Matriculas;
import com.pi.model.Pessoas;
import com.pi.repository.MatriculasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MatriculasService {

    private static final Logger logger = LoggerFactory.getLogger(MatriculasService.class);

    @Autowired
    private MatriculasRepository matriculasRepository;
    @Autowired
    private PessoasService pessoasService;
    @Autowired
    private DisciplinasService disciplinasService;

    public List<MatriculaResponse> getAllMatriculas() {
        List<Matriculas> matriculas = matriculasRepository.findAll();
        return matriculas.stream()
                .map(MatriculaResponse::of)
                .toList();
    }

    public void save(MatriculaRequest request) {
        var aluno = validarAluno(request.getAlunoId());
        var disciplina = validarDisciplina(request.getDisciplinaId());

        validarAlunoJaMatriculado(aluno, disciplina);
        validarLimiteAluno(disciplina);

        var matricula = Matriculas.of(request, aluno, disciplina);

        matriculasRepository.save(matricula);
    }

    public void edit(MatriculaRequest request) {
        var aluno = validarAluno(request.getAlunoId());
        var disciplina = validarDisciplina(request.getDisciplinaId());

        validarAlunoJaMatriculado(aluno, disciplina);
        validarLimiteAluno(disciplina);

        var matricula = Matriculas.of(request, aluno, disciplina);
        BeanUtils.copyProperties(request, matricula);
        matriculasRepository.save(matricula);
    }

    public MatriculaResponse getMatriculasByID(int idMat) {
        var matricula = matriculasRepository.findById(idMat)
                .orElseThrow(() -> new ValidacaoException("Matricula não encontrada!"));
        return MatriculaResponse.of(matricula);
    }

    public void deleteMatriculasByID(int idMat) {
        this.matriculasRepository.deleteById(idMat);
    }

    public Double getFaturamentoPorPeriodo(int disciplinaId, String dataInicio, String dataFim) {
        var dataInicial = converterData(dataInicio).atStartOfDay();
        var dataFinal = converterData(dataFim).atTime(LocalTime.MAX);

        return matriculasRepository.findTotalRevenueByDisciplinaAndPeriod(disciplinaId, dataInicial, dataFinal);
    }

    private Pessoas validarAluno(int id) {
        return pessoasService.getById(id);
    }

    private Disciplinas validarDisciplina(int id) {
        return disciplinasService.getById(id);
    }

    private void validarLimiteAluno(Disciplinas disciplina){
        var matriculas = matriculasRepository.findByDisciplina(disciplina);
        logger.info("Quantida de matriculas: " + matriculas.size());
        logger.info("Limite alunos: " + disciplina.getLmtAlunos());

        if (matriculas.size() >= disciplina.getLmtAlunos()) {
            throw new ValidacaoException("O limite de alunos para esta disciplina já foi atingido.");
        }
    }
    
    private void validarAlunoJaMatriculado(Pessoas aluno, Disciplinas disciplina) {
        if (matriculasRepository.existsByAlunoAndDisciplina(aluno, disciplina)) {
            throw new ValidacaoException("O aluno já está matriculado nesta disciplina.");
        }
    }

    private LocalDate converterData(String data) {
        logger.info("DATA PASSADA NO PARAMETRO: " + data);

        var format = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return LocalDate.parse(data, format);
    }
}
