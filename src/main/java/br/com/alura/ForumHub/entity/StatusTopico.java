package br.com.alura.ForumHub.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusTopico {

    NAO_RESOLVIDO("Não resolvido"),
    RESOLVIDO("Resolvido");

    private final String descricao;

    StatusTopico(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}