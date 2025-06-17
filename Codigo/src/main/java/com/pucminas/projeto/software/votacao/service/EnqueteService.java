package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import com.pucminas.projeto.software.votacao.repository.EnqueteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnqueteService {
    private final EnqueteRepository enqueteRepository;

    public EnqueteService(EnqueteRepository enqueteRepository) {
        this.enqueteRepository = enqueteRepository;
    }

    @Transactional
    public Enquete criarEnquete(Enquete enquete) {
        return enqueteRepository.save(enquete);
    }

    @Transactional(readOnly = true)
    public List<Enquete> listarEnquetes() {
        return enqueteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Enquete buscarEnquete(Long id) {
        return enqueteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquete não encontrada"));
    }

    @Transactional
    public Enquete adicionarOpcao(Long enqueteId, OpcaoVoto opcaoVoto) {
        Enquete enquete = buscarEnquete(enqueteId);
        opcaoVoto.setEnquete(enquete);
        enquete.getOpcoes().add(opcaoVoto);
        return enqueteRepository.save(enquete);
    }

    @Transactional
    public Enquete finalizarEnquete(Long id) {
        Enquete enquete = buscarEnquete(id);
        if (!enquete.isAtiva()) {
            throw new RuntimeException("Enquete já está finalizada");
        }
        enquete.setAtiva(false);
        return enqueteRepository.save(enquete);
    }
} 