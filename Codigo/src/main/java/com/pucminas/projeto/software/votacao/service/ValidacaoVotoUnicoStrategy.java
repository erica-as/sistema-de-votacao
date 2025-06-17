package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Voto;
import com.pucminas.projeto.software.votacao.repository.VotoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoVotoUnicoStrategy implements ValidacaoVotoStrategy {
    private final VotoRepository votoRepository;

    public ValidacaoVotoUnicoStrategy(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public void validar(Voto voto) {
        if (votoRepository.existsByEnqueteIdAndCpfVotante(voto.getEnquete().getId(), voto.getCpfVotante())) {
            throw new RuntimeException("Usuário já votou nesta enquete");
        }
    }
} 