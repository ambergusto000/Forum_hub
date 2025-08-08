package br.com.alura.ForumHub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record RespostaResponseDTO(
        Long id,
        String mensagem,
        String autor,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataCriacao
) {}