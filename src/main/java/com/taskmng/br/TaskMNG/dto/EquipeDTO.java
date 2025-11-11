package com.taskmng.br.TaskMNG.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.taskmng.br.TaskMNG.entities.Equipe;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EquipeDTO(
        String nomeEquipe,
        String nomeProjeto,
        String nomeTechLead
) {
    public EquipeDTO(Equipe equipe) {
        this(
                equipe.getNomeEquipe(),
                equipe.getProjeto() != null ? equipe.getProjeto().getDescricaoProjeto() : null,
                equipe.getIdTechLead() != null ? equipe.getIdTechLead().getNome() : null
        );
    }
}