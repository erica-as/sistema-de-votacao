package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Voto;

public interface ValidacaoVotoStrategy {
    void validar(Voto voto);
} 