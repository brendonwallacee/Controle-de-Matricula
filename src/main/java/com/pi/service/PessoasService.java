package com.pi.service;

import com.pi.dto.PessoaRequest;
import com.pi.dto.PessoaResponse;
import com.pi.exceptions.exception.ValidacaoException;
import com.pi.model.Pessoas;
import com.pi.repository.DisciplinasRepository;
import com.pi.repository.MatriculasRepository;
import com.pi.repository.PessoasRepository;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoasService{

    @Autowired
    private PessoasRepository pessoasRepository;
    @Autowired
    private MatriculasRepository matriculasRepository;
    @Autowired
    private DisciplinasRepository disciplinasRepository;

    public List<PessoaResponse> getAllPessoas() {
        return pessoasRepository.findAll()
                .stream().map(PessoaResponse::of)
                .toList();
    }

    public void save(PessoaRequest request) {

        this.pessoasRepository.save(Pessoas.of(request));
    }

    public void edit(PessoaRequest request) {
        var pessoa = getById(request.getId());
        BeanUtils.copyProperties(request, pessoa);
        pessoasRepository.save(pessoa);
    }

    public PessoaResponse getPessoasByID(int idPessoa) {
        var pessoa = getById(idPessoa);

        return PessoaResponse.of(pessoa);
    }

    public void deletePessoasByID(int idPessoa) {
        var pessoa = getById(idPessoa);

        validarExistenciaPessoa(pessoa);

        pessoasRepository.delete(pessoa);
    }

    public Pessoas getById(int id) {
        var pessoa = pessoasRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Pessoa não encontrada"));

        return pessoa;
    }

    private void validarExistenciaPessoa(Pessoas pessoa) {
        var hasMatriculas = matriculasRepository.existsByAluno(pessoa);
        var isProfessor = disciplinasRepository.existsByProfessor(pessoa);

        if (hasMatriculas || isProfessor) {
            throw new ValidacaoException("Não é possível excluir a pessoa, pois há matrículas ou disciplinas associadas.");
        }
    }

}
