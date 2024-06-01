package com.pi.controller;

import com.pi.model.Pessoas;
import com.pi.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pessoas")
public class PessoasController {

    @Autowired
    private PessoasService pessoasService;

    @GetMapping
    public List<Pessoas> buscarTodos() {
        return pessoasService.getAllPessoas();
    }

    @PostMapping("salvar")
    public void salvarPessoa(@RequestBody Pessoas pessoas) {
        pessoasService.save(pessoas);
    }

    @GetMapping("{id}")
    public Pessoas getById(@PathVariable int id) {
        return pessoasService.getPessoasByID(id);
    }

    @GetMapping("{id}/delete")
    public void deleteId(@PathVariable int id) {
        pessoasService.deletePessoasByID(id);
    }
}

