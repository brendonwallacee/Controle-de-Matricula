package com.pi.service;

import com.pi.model.Matriculas;
import com.pi.repository.MatriculasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatriculasService{

    @Autowired
    private MatriculasRepository matriculasRepository;

    public List<Matriculas> getAllMatriculas() {
        return matriculasRepository.findAll();
    }

    public void save(Matriculas mat) {
        this.matriculasRepository.save(mat);
    }

    public Matriculas getMatriculasByID(int idMat) {
        Optional<Matriculas> optional = matriculasRepository.findById(idMat);
        Matriculas matricula = null;
        if (optional.isPresent()) {
            matricula = optional.get();

        }
        else {
            throw new RuntimeException("Matricula não encontrada!");

        }

        return matricula;

    }

    public void deleteMatriculasByID(int idMat) {
        this.matriculasRepository.deleteById(idMat);
    }

}
