package br.com.alura.ForumHub.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record AtualizarUsuarioRequestDTO(
        @NotNull
        Long id,
        @NotBlank
        String username,
        @NotBlank
        String senha,
        @NotBlank
        String curso
) {
}