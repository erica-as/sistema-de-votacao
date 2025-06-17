package com.pucminas.projeto.software.votacao.controller;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import com.pucminas.projeto.software.votacao.service.EnqueteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enquetes")
@Tag(name = "Enquetes", description = "API para gerenciamento de enquetes")
public class EnqueteController {
    private final EnqueteService enqueteService;

    public EnqueteController(EnqueteService enqueteService) {
        this.enqueteService = enqueteService;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova enquete")
    public ResponseEntity<Enquete> criarEnquete(@RequestBody Enquete enquete) {
        return ResponseEntity.ok(enqueteService.criarEnquete(enquete));
    }

    @GetMapping
    @Operation(summary = "Listar todas as enquetes")
    public ResponseEntity<List<Enquete>> listarEnquetes() {
        return ResponseEntity.ok(enqueteService.listarEnquetes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma enquete por ID")
    public ResponseEntity<Enquete> buscarEnquete(@PathVariable Long id) {
        return ResponseEntity.ok(enqueteService.buscarEnquete(id));
    }

    @PostMapping("/{id}/opcoes")
    @Operation(summary = "Adicionar uma opção de voto a uma enquete")
    public ResponseEntity<Enquete> adicionarOpcao(@PathVariable Long id, @RequestBody OpcaoVoto opcaoVoto) {
        return ResponseEntity.ok(enqueteService.adicionarOpcao(id, opcaoVoto));
    }
} 