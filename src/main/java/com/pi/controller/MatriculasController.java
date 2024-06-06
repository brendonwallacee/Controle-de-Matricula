package com.pi.controller;

import com.pi.dto.MatriculaRequest;
import com.pi.dto.MatriculaResponse;
import com.pi.model.Matriculas;
import com.pi.service.MatriculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculasController {

    @Autowired
    private MatriculasService matriculasService;

    @GetMapping
    public List<MatriculaResponse> buscarTodos() {
        return matriculasService.getAllMatriculas();
    }

    @PostMapping("salvar")
    public void salvarMatricula(@RequestBody MatriculaRequest request) {
        matriculasService.save(request);
    }

    @PutMapping("editar")
    public void editarMatricula(@RequestBody MatriculaRequest request) {
        matriculasService.edit(request);
    }

    @GetMapping("{id}")
    public MatriculaResponse getById(@PathVariable int id) {
        return matriculasService.getMatriculasByID(id);
    }

    @DeleteMapping("{id}/delete")
    public void deleteId(@PathVariable int id) {
        matriculasService.deleteMatriculasByID(id);
    }

    @GetMapping("/faturamento")
    public Double getFaturamento(@RequestParam int disciplinaId,
                                 @RequestParam String dataInicio,
                                 @RequestParam String dataFim) {
        return matriculasService.getFaturamentoPorPeriodo(disciplinaId, dataInicio, dataFim);
    }
}

