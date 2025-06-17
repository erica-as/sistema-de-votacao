package com.pucminas.projeto.software.votacao.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "enquetes")
public class Enquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataFim;

    @OneToMany(mappedBy = "enquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpcaoVoto> opcoes = new ArrayList<>();

    @OneToMany(mappedBy = "enquete", cascade = CascadeType.ALL)
    private List<Voto> votos = new ArrayList<>();

    @Column(nullable = false)
    private boolean ativa = true;
} 