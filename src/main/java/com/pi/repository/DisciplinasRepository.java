package com.pi.repository;

import com.pi.model.Disciplinas;
import com.pi.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinasRepository extends JpaRepository<Disciplinas, Integer> {

    boolean existsByProfessor(Pessoas professor);

    @Query("SELECT d FROM Disciplinas d WHERE d.professor.id = :professorId")
    List<Disciplinas> findByProfessorId(@Param("professorId") int professorId);

    @Query("SELECT DISTINCT m.disciplina FROM Matriculas m WHERE m.aluno.id = :alunoId")
    List<Disciplinas> findByAlunoId(@Param("alunoId") int alunoId);
}
