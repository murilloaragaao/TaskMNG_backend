package com.taskmng.br.TaskMNG.controllers;

import com.taskmng.br.TaskMNG.dto.EquipeDTO;
import com.taskmng.br.TaskMNG.entities.Equipe;
import com.taskmng.br.TaskMNG.entities.Usuario;
import com.taskmng.br.TaskMNG.service.EquipeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskmng/equipe")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    @PostMapping("/criar")
    public ResponseEntity<EquipeDTO> criarEquipe(
            @RequestBody Equipe novaEquipe,
            @RequestParam Long idProjeto,
            @RequestParam Long idTechLead,
            HttpServletRequest request) {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        EquipeDTO criada = equipeService.criarEquipe(novaEquipe, idProjeto, idTechLead, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EquipeDTO>> listarEquipes() {
        return ResponseEntity.ok(equipeService.listarEquipes());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EquipeDTO> atualizarEquipe(
            @PathVariable Long id,
            @RequestBody Equipe equipeAtualizada,
            HttpServletRequest request) {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        EquipeDTO atualizada = equipeService.atualizarEquipe(id, equipeAtualizada, usuarioLogado);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarEquipe(
            @PathVariable Long id,
            HttpServletRequest request) {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        equipeService.deletarEquipe(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }
}
