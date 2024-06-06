package com.pi.service;

import com.pi.dto.DisciplinaRequest;
import com.pi.dto.DisciplinaResponse;
import com.pi.exceptions.exception.ValidacaoException;
import com.pi.model.Disciplinas;
import com.pi.model.Pessoas;
import com.pi.repository.DisciplinasRepository;
import com.pi.repository.MatriculasRepository;
import com.pi.repository.PessoasRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinasService {

    @Autowired
    private DisciplinasRepository disciplinasRepository;
    @Autowired
    private MatriculasRepository matriculasRepository;
    @Autowired
    private PessoasService pessoasService;

    public List<DisciplinaResponse> getAllDisciplinas() {
        return disciplinasRepository.findAll()
                .stream()
                .map(DisciplinaResponse::of)
                .toList();
    }

    public void save(DisciplinaRequest request) {
        var professor = validarProfessor(request.getProfessorId());

        var disciplina = Disciplinas.of(request, professor);

        disciplinasRepository.save(disciplina);
    }

    public void edit(DisciplinaRequest request) {
        var professor = validarProfessor(request.getProfessorId());
        var disciplina = Disciplinas.of(request, professor);

        BeanUtils.copyProperties(request, disciplina);
        disciplinasRepository.save(disciplina);
    }

    public DisciplinaResponse getDisciplinasByID(int codigo) {
        var disciplina = getById(codigo);
        return DisciplinaResponse.of(disciplina);
    }

    public void deleteDisciplinasByID(int codigo) {
        var disciplina = getById(codigo);

        validarExistenciaDeMatricula(disciplina);

        disciplinasRepository.delete(disciplina);
    }

    public Disciplinas getById(int id) {
        return disciplinasRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Disciplina não encontrada!"));
    }

    public List<Disciplinas> getDisciplinasByProfessor(int professorId) {
        var professor = pessoasService.getById(professorId);
        return disciplinasRepository.findByProfessorId(professor.getId());
    }

    public List<Disciplinas> getDisciplinasByAluno(int alunoId) {

        var aluno = pessoasService.getById(alunoId);
        return disciplinasRepository.findByAlunoId(aluno.getId());
    }

    private Pessoas validarProfessor(int id) {
        return pessoasService.getById(id);
    }

    private void validarExistenciaDeMatricula(Disciplinas disciplina) {
        var hasMatriculas = matriculasRepository.existsByDisciplina(disciplina);

        if (hasMatriculas) {
            throw new ValidacaoException("Não é possível excluir a disciplina, pois há matrículas associadas.");
        }
    }
}

