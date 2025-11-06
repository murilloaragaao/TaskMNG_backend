package com.taskmng.br.TaskMNG.dto;

import com.taskmng.br.TaskMNG.enums.Prioridade;
import com.taskmng.br.TaskMNG.enums.Status;

import java.util.Date;

public record TarefaDTO(
        String nomeTarefa,
        String descricao,
        Date dataCriacao,
        Date dataEntrega,
        Prioridade prioridade,
        Status status
) {}
