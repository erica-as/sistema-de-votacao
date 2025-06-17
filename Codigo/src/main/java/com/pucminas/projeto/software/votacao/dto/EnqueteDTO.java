package com.pucminas.projeto.software.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO para criação e atualização de enquetes")
public class EnqueteDTO {
    
    @Schema(description = "Título da enquete", example = "Eleições 2026")
    private String titulo;
    
    @Schema(description = "Descrição detalhada da enquete", example = "Votação para presidente da república")
    private String descricao;
    
    @Schema(description = "Data de início da enquete", example = "2024-01-01T00:00:00")
    private LocalDateTime dataInicio;
    
    @Schema(description = "Data de término da enquete", example = "2024-01-02T00:00:00")
    private LocalDateTime dataFim;
    
    @Schema(description = "Lista de opções de voto")
    private List<OpcaoVotoDTO> opcoes;

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public List<OpcaoVotoDTO> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<OpcaoVotoDTO> opcoes) {
        this.opcoes = opcoes;
    }
} 