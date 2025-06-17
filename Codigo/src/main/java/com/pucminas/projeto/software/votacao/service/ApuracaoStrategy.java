package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import java.util.Map;

public interface ApuracaoStrategy {
    Map<OpcaoVoto, Long> apurar(Enquete enquete);
} 