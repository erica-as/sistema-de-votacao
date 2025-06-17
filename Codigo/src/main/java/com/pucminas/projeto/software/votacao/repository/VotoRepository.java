package com.pucminas.projeto.software.votacao.repository;

import com.pucminas.projeto.software.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByEnqueteIdAndCpfVotante(Long enqueteId, String cpfVotante);
} 