package com.pi.controller;

import com.pi.dto.DisciplinaRequest;
import com.pi.dto.DisciplinaResponse;
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
    public List<DisciplinaResponse> buscarTodos() {
        return disciplinasService.getAllDisciplinas();
    }

    @PostMapping("salvar")
    public void salvarDisciplina(@RequestBody DisciplinaRequest request) {
        disciplinasService.save(request);
    }

    @PutMapping("editar")
    public void editarDisciplina(@RequestBody DisciplinaRequest request) {
        disciplinasService.edit(request);
    }

    @GetMapping("{id}")
    public DisciplinaResponse getById(@PathVariable int id) {
        return disciplinasService.getDisciplinasByID(id);
    }

    @DeleteMapping("{id}/delete")
    public void deleteId(@PathVariable int id) {
        disciplinasService.deleteDisciplinasByID(id);
    }

    @GetMapping("/professor/{professorId}")
    public List<Disciplinas> getDisciplinasByProfessor(@PathVariable int professorId) {
        return disciplinasService.getDisciplinasByProfessor(professorId);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Disciplinas> getDisciplinasByAluno(@PathVariable int alunoId) {
        return disciplinasService.getDisciplinasByAluno(alunoId);
    }
}
