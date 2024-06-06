package com.pi.controller;

import com.pi.dto.PessoaRequest;
import com.pi.dto.PessoaResponse;
import com.pi.service.PessoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    @Autowired
    private PessoasService pessoasService;

    @GetMapping
    public List<PessoaResponse> buscarTodos() {
        return pessoasService.getAllPessoas();
    }

    @PostMapping("salvar")
    public void salvarPessoa(@RequestBody PessoaRequest request) {
        pessoasService.save(request);
    }

    @PutMapping("editar")
    public void editarPessoa(@RequestBody PessoaRequest request) {
        pessoasService.edit(request);
    }

    @GetMapping("{id}")
    public PessoaResponse getById(@PathVariable int id) {
        return pessoasService.getPessoasByID(id);
    }

    @DeleteMapping("{id}/delete")
    public void deleteId(@PathVariable int id) {
        pessoasService.deletePessoasByID(id);
    }


}

