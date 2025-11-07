package com.taskmng.br.TaskMNG.dto;

import com.taskmng.br.TaskMNG.enums.Prioridade;
import com.taskmng.br.TaskMNG.enums.Status;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public record TarefaUpdateDTO(
        @NotBlank
        String nomeTarefa,
        @NotBlank
        String descricao,
        Date dataEntrega,
        @NotNull
        Prioridade prioridade,
        @NotNull
        Status status
) { }
