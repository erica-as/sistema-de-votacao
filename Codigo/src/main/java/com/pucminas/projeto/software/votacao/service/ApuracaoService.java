package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import com.pucminas.projeto.software.votacao.repository.EnqueteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ApuracaoService {
    private final EnqueteRepository enqueteRepository;
    private final Map<TipoApuracao, ApuracaoStrategy> estrategias;

    public ApuracaoService(EnqueteRepository enqueteRepository, 
                          Map<TipoApuracao, ApuracaoStrategy> estrategias) {
        this.enqueteRepository = enqueteRepository;
        this.estrategias = estrategias;
    }

    @Transactional(readOnly = true)
    public Map<OpcaoVoto, Long> apurarResultados(Long enqueteId, TipoApuracao tipoApuracao) {
        Enquete enquete = enqueteRepository.findById(enqueteId)
                .orElseThrow(() -> new RuntimeException("Enquete não encontrada"));
        
        ApuracaoStrategy estrategia = estrategias.get(tipoApuracao);
        if (estrategia == null) {
            throw new RuntimeException("Estratégia de apuração não encontrada");
        }
        
        return estrategia.apurar(enquete);
    }
} 