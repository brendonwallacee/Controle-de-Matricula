package com.pi.repository;

import com.pi.model.Disciplinas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinasRepository extends JpaRepository<Disciplinas, Integer> {

}
