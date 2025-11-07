package com.taskmng.br.TaskMNG.repository;

import com.taskmng.br.TaskMNG.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
