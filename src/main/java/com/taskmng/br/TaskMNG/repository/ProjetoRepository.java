package com.taskmng.br.TaskMNG.repository;

import com.taskmng.br.TaskMNG.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
