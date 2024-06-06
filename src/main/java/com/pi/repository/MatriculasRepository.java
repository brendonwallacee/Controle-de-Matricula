package com.pi.repository;

import com.pi.model.Disciplinas;
import com.pi.model.Matriculas;
import com.pi.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface MatriculasRepository extends JpaRepository<Matriculas, Integer> {

    List<Matriculas> findByDisciplina(Disciplinas disciplina);

    boolean existsByDisciplina(Disciplinas disciplina);

    boolean existsByAluno(Pessoas aluno);

    boolean existsByAlunoAndDisciplina(Pessoas aluno, Disciplinas disciplina);

    @Query("SELECT SUM(m.valorPago) FROM Matriculas m WHERE m.disciplina.id = :disciplinaId AND m.dataMatricula BETWEEN :startDate AND :endDate")
    Double findTotalRevenueByDisciplinaAndPeriod(@Param("disciplinaId") int disciplinaId,
                                                 @Param("startDate") LocalDateTime startDate,
                                                 @Param("endDate") LocalDateTime endDate);
}
