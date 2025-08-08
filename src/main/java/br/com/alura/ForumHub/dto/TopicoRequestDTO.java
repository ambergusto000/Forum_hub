package br.com.alura.ForumHub.dto;

import br.com.alura.ForumHub.entity.Topico;
import jakarta.validation.constraints.NotBlank;

public record TopicoRequestDTO(
        @NotBlank(message = "Titulo é obrigatório")
        String titulo,
        @NotBlank(message = "Mensagem é obrigatória")
        String mensagem,
        @NotBlank(message = "Curso é obrigatório")
        String curso
) {
    public TopicoRequestDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getCurso());
    }
}