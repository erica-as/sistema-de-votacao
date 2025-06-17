package com.pucminas.projeto.software.votacao.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "votos")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enquete_id", nullable = false)
    private Enquete enquete;

    @ManyToOne
    @JoinColumn(name = "opcao_voto_id", nullable = false)
    private OpcaoVoto opcaoVoto;

    @Column(nullable = false)
    private String cpfVotante;

    @Column(nullable = false)
    private LocalDateTime dataVoto;

    public Voto() {
        this.dataVoto = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enquete getEnquete() {
        return enquete;
    }

    public void setEnquete(Enquete enquete) {
        this.enquete = enquete;
    }

    public OpcaoVoto getOpcao() {
        return opcaoVoto;
    }

    public void setOpcao(OpcaoVoto opcao) {
        this.opcaoVoto = opcao;
    }

    public String getCpfVotante() {
        return cpfVotante;
    }

    public void setCpfVotante(String cpfVotante) {
        this.cpfVotante = cpfVotante;
    }

    public LocalDateTime getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(LocalDateTime dataVoto) {
        this.dataVoto = dataVoto;
    }
} 