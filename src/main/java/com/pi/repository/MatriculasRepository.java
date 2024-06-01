package com.pi.repository;

import com.pi.model.Matriculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatriculasRepository extends JpaRepository<Matriculas, Integer> {

}
