package com.taskmng.br.TaskMNG.service;

import com.taskmng.br.TaskMNG.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;
}
