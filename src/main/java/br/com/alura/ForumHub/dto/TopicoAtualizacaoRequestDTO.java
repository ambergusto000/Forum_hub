package br.com.alura.ForumHub.dto;

import br.com.alura.ForumHub.entity.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoAtualizacaoRequestDTO(
        @NotNull Long id,
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String curso
) {
    public TopicoAtualizacaoRequestDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso());
    }
}