package com.pi.controller;

import com.pi.model.Matriculas;
import com.pi.service.MatriculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculasController {

    @Autowired
    private MatriculasService matriculasService;

    @GetMapping
    public List<Matriculas> buscarTodos() {
        return matriculasService.getAllMatriculas();
    }

    @PostMapping("salvar")
    public void salvarMatricula(@RequestBody Matriculas matriculas) {
        matriculasService.save(matriculas);
    }

    @GetMapping("{id}")
    public Matriculas getById(@PathVariable int id) {
        return matriculasService.getMatriculasByID(id);
    }

    @DeleteMapping("{id}/delete")
    public void deleteId(@PathVariable int id) {
        matriculasService.deleteMatriculasByID(id);
    }
}

