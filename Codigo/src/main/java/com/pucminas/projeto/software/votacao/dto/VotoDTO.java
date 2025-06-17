package com.pucminas.projeto.software.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para registro de votos")
public class VotoDTO {
    
    @Schema(description = "ID da opção de voto escolhida", example = "1")
    private Long opcaoId;
    
    @Schema(description = "CPF do votante", example = "12345678900")
    private String cpfVotante;

    public Long getOpcaoId() {
        return opcaoId;
    }

    public void setOpcaoId(Long opcaoId) {
        this.opcaoId = opcaoId;
    }

    public String getCpfVotante() {
        return cpfVotante;
    }

    public void setCpfVotante(String cpfVotante) {
        this.cpfVotante = cpfVotante;
    }
} 