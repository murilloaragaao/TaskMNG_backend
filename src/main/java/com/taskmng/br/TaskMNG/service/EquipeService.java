package com.taskmng.br.TaskMNG.service;

import com.taskmng.br.TaskMNG.dto.EquipeDTO;
import com.taskmng.br.TaskMNG.entities.Equipe;
import com.taskmng.br.TaskMNG.entities.Projeto;
import com.taskmng.br.TaskMNG.entities.Usuario;
import com.taskmng.br.TaskMNG.enums.Perfil;
import com.taskmng.br.TaskMNG.repository.EquipeRepository;
import com.taskmng.br.TaskMNG.repository.ProjetoRepository;
import com.taskmng.br.TaskMNG.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeRepository equipeRepository;
    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public EquipeDTO criarEquipe(Equipe novaEquipe, Long idProjeto, Long idTechLead, Usuario usuarioLogado) {
        if (usuarioLogado == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, " Usuário não autenticado.");

        if (usuarioLogado.getTipoPerfil() != Perfil.ADMINISTRADOR &&
                usuarioLogado.getTipoPerfil() != Perfil.TECHLEAD)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, " Apenas ADMINISTRADOR ou TECHLEAD podem criar equipes.");

        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Projeto não encontrado."));

        Usuario techLead = usuarioRepository.findById(idTechLead)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Techlead não encontrado."));

        if (techLead.getTipoPerfil() != Perfil.TECHLEAD)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Usuário selecionado não é um TECHLEAD.");
        novaEquipe.setProjeto(projeto);
        novaEquipe.setIdTechLead(techLead);
        novaEquipe.setAtivo(1);

        Equipe salvo = equipeRepository.save(novaEquipe);
        return new EquipeDTO(salvo);
    }

    public List<EquipeDTO> listarEquipes() {
        return equipeRepository.findAll()
                .stream()
                .filter(e -> e.getAtivo() == 1)
                .map(EquipeDTO::new)
                .toList();
    }

    @Transactional
    public EquipeDTO atualizarEquipe(Long id, Equipe equipeAtualizada, Usuario usuarioLogado) {
        if (usuarioLogado == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, " Usuário não autenticado.");

        if (usuarioLogado.getTipoPerfil() != Perfil.ADMINISTRADOR &&
                usuarioLogado.getTipoPerfil() != Perfil.TECHLEAD)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, " Apenas ADMINISTRADOR ou TECHLEAD podem atualizar equipes.");

        Equipe existente = equipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Equipe não encontrada."));

        existente.setNomeEquipe(equipeAtualizada.getNomeEquipe());
        existente.setAtivo(equipeAtualizada.getAtivo());

        Equipe atualizada = equipeRepository.save(existente);
        return new EquipeDTO(atualizada);
    }

    @Transactional
    public void deletarEquipe(Long id, Usuario usuarioLogado) {
        if (usuarioLogado == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, " Usuário não autenticado.");

        if (usuarioLogado.getTipoPerfil() != Perfil.ADMINISTRADOR)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, " Apenas ADMIN pode excluir equipes.");

        Equipe equipe = equipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Equipe não encontrada."));

        if (equipe.getAtivo() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Equipe já está inativa.");

        equipe.setAtivo(0);
        equipeRepository.save(equipe);
    }
}
