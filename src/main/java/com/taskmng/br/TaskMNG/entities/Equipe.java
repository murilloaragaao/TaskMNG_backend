package com.taskmng.br.TaskMNG.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Equipe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe")
    private Long idEquipe;

    @Column(name = "nome_equipe")
    @NotBlank
    private String nomeEquipe;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Usuario idColaborador;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "techlead_id", nullable = false)
    private Usuario idTechLead;

    @NotNull
    private Integer ativo;
}
