package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import com.pucminas.projeto.software.votacao.model.Voto;
import com.pucminas.projeto.software.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VotoService {
    private final VotoRepository votoRepository;
    private final EnqueteService enqueteService;
    private final ValidacaoVotoStrategy validacaoVotoStrategy;

    public VotoService(VotoRepository votoRepository, 
                      EnqueteService enqueteService,
                      ValidacaoVotoStrategy validacaoVotoStrategy) {
        this.votoRepository = votoRepository;
        this.enqueteService = enqueteService;
        this.validacaoVotoStrategy = validacaoVotoStrategy;
    }

    @Transactional
    public Voto registrarVoto(Long enqueteId, Long opcaoVotoId, String cpfVotante) {
        Enquete enquete = enqueteService.buscarEnquete(enqueteId);
        OpcaoVoto opcaoVoto = enquete.getOpcoes().stream()
                .filter(op -> op.getId().equals(opcaoVotoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Opção de voto não encontrada"));

        Voto voto = new Voto();
        voto.setEnquete(enquete);
        voto.setOpcaoVoto(opcaoVoto);
        voto.setCpfVotante(cpfVotante);
        voto.setDataVoto(LocalDateTime.now());

        validacaoVotoStrategy.validar(voto);
        return votoRepository.save(voto);
    }

    public List<Voto> listarVotosPorEnquete(Long enqueteId) {
        return votoRepository.findAll().stream()
                .filter(voto -> voto.getEnquete().getId().equals(enqueteId))
                .toList();
    }

    @Transactional(readOnly = true)
    public Map<Long, Long> listarVotos(Long enqueteId) {
        Enquete enquete = enqueteService.buscarEnquete(enqueteId);
        return enquete.getVotos().stream()
                .collect(Collectors.groupingBy(
                    voto -> voto.getOpcaoVoto().getId(),
                    Collectors.counting()
                ));
    }
} 