package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.dto.RespostaRequestDTO;
import br.com.alura.ForumHub.dto.RespostaResponseDTO;
import br.com.alura.ForumHub.infra.ValidacaoException;
import br.com.alura.ForumHub.repository.RespostaRepository;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import br.com.alura.ForumHub.service.RespostaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/topicos/{idTopico}/resposta")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity<RespostaResponseDTO> responderTopico(@PathVariable Long idTopico, @RequestBody @Valid RespostaRequestDTO respostaRequestDTO, Authentication authentication){

        String username = authentication.getName();

        var autor = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ValidacaoException("Erro ao carregar usuário"));
        var dto = respostaService.criar(idTopico,autor , respostaRequestDTO.mensagem() );
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarResposta(@PathVariable Long id) {
        respostaService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}