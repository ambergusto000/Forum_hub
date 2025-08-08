package br.com.alura.ForumHub.dto;

import br.com.alura.ForumHub.entity.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String username,
        String curso
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getCurso());
    }
}