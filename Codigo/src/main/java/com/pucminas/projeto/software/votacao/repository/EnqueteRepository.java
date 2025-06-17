package com.pucminas.projeto.software.votacao.repository;

import com.pucminas.projeto.software.votacao.model.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnqueteRepository extends JpaRepository<Enquete, Long> {
    
    @Query("SELECT DISTINCT e FROM Enquete e LEFT JOIN FETCH e.opcoes")
    List<Enquete> findAll();
    
    @Query("SELECT e FROM Enquete e LEFT JOIN FETCH e.opcoes WHERE e.id = :id")
    Optional<Enquete> findById(Long id);
} 