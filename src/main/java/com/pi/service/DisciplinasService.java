package com.pi.service;

import com.pi.model.Disciplinas;
import com.pi.repository.DisciplinasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinasService {

    @Autowired
    private DisciplinasRepository disciplinasRepository;

    public List<Disciplinas> getAllDisciplinas() {
        return disciplinasRepository.findAll();
    }

    public void save(Disciplinas dsp) {

        disciplinasRepository.save(dsp);
    }

    public Disciplinas getDisciplinasByID(int codigo) {
        Optional<Disciplinas> optional = disciplinasRepository.findById(codigo);
        Disciplinas disciplina = null;
        if (optional.isPresent()) {
            disciplina = optional.get();
        }
        else {
            throw new RuntimeException("Disciplina não encontrada!");
        }

        return disciplina;

    }

    public void deleteDisciplinasByID(int codigo) {
        this.disciplinasRepository.deleteById(codigo);
    }

}

