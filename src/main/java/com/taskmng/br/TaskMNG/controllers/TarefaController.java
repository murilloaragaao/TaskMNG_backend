package com.taskmng.br.TaskMNG.controllers;

import com.taskmng.br.TaskMNG.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskmng/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
}
