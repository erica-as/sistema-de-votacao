package com.pucminas.projeto.software.votacao.controller;

import com.pucminas.projeto.software.votacao.dto.VotoDTO;
import com.pucminas.projeto.software.votacao.model.Voto;
import com.pucminas.projeto.software.votacao.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enquetes/{enqueteId}/votos")
@Tag(name = "Votos", description = "API para registro e consulta de votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    @Operation(summary = "Registrar voto", description = "Registra um novo voto em uma enquete")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Voto registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Enquete ou opção não encontrada"),
        @ApiResponse(responseCode = "409", description = "Votante já registrou voto nesta enquete"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Voto> registrarVoto(
            @Parameter(description = "ID da enquete", example = "1") @PathVariable Long enqueteId,
            @Parameter(description = "Dados do voto") @RequestBody VotoDTO votoDTO) {
        Voto voto = votoService.registrarVoto(enqueteId, votoDTO.getOpcaoId(), votoDTO.getCpfVotante());
        return ResponseEntity.ok(voto);
    }

    @GetMapping
    @Operation(summary = "Listar votos", description = "Retorna todos os votos de uma enquete")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de votos retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Enquete não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<Long, Long>> listarVotos(
            @Parameter(description = "ID da enquete", example = "1") @PathVariable Long enqueteId) {
        return ResponseEntity.ok(votoService.listarVotos(enqueteId));
    }
} 