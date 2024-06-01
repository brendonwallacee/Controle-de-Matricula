package com.pi.service;

import com.pi.model.Pessoas;
import com.pi.repository.PessoasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PessoasService{

    @Autowired
    private PessoasRepository pessoasRepository;

    public List<Pessoas> getAllPessoas() {
        return pessoasRepository.findAll();
    }

    public void save(Pessoas ps) {
        this.pessoasRepository.save(ps);
    }

    public Pessoas getPessoasByID(int idPessoa) {
        Optional<Pessoas> optional = pessoasRepository.findById(idPessoa);
        Pessoas pessoa = null;
        if (optional.isPresent()) {
            pessoa = optional.get();
        }
        else {
            throw new RuntimeException("Pessoa não encontrada");
        }

        return pessoa;
    }

    public void deletePessoasByID(int idPessoa) {
        this.pessoasRepository.deleteById(idPessoa);
    }
}
