package br.com.alura.ForumHub.dto;

import jakarta.validation.constraints.NotBlank;

public record RespostaRequestDTO(
        @NotBlank
        String mensagem
) {}