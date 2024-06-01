package com.pi.controller;

import com.pi.model.Disciplinas;
import com.pi.service.DisciplinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinasController {

    @Autowired
    private DisciplinasService disciplinasService;

    @GetMapping
    public List<Disciplinas> buscarTodos() {
        return disciplinasService.getAllDisciplinas();
    }

    @PostMapping("salvar")
    public void salvarDisciplina(@RequestBody Disciplinas disciplinas) {
        disciplinasService.save(disciplinas);
    }

    @GetMapping("{id}")
    public Disciplinas getById(@PathVariable int id) {
        return disciplinasService.getDisciplinasByID(id);
    }

    @GetMapping("{id}/delete")
    public void deleteId(@PathVariable int id) {
        disciplinasService.deleteDisciplinasByID(id);
    }
}
