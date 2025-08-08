package br.com.alura.ForumHub.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotNull(message = "Username é obrigatório")
        String username,
        @NotNull(message = "Senha é obrigatória")
        String senha,
        @NotNull(message = "Curso é obrigatório")
        String curso
) { }