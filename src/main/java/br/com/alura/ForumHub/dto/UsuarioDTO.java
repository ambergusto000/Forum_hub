package br.com.alura.ForumHub.dto;

import br.com.alura.ForumHub.entity.Usuario;

public record UsuarioDTO(

        Usuario username
) {
    public UsuarioDTO(Usuario username) {
        this.username = username;
    }

}