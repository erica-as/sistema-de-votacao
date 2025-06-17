package com.pucminas.projeto.software.votacao.service;

public enum TipoApuracao {
    SIMPLES("Contagem simples de votos"),
    PORCENTAGEM("Porcentagem de votos"),
    PERIODO("Votos do último dia");

    private final String descricao;

    TipoApuracao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
} 