package com.pucminas.projeto.software.votacao.dto;

import lombok.Data;

@Data
public class OpcaoVotoDTO {
    private Long id;
    private String descricao;
    private Long enqueteId;
} 