package com.pucminas.projeto.software.votacao.cli;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import com.pucminas.projeto.software.votacao.service.ApuracaoService;
import com.pucminas.projeto.software.votacao.service.EnqueteService;
import com.pucminas.projeto.software.votacao.service.TipoApuracao;
import com.pucminas.projeto.software.votacao.service.VotoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
@Order(1)
public class VotacaoCLI implements CommandLineRunner {

    private final EnqueteService enqueteService;
    private final VotoService votoService;
    private final ApuracaoService apuracaoService;
    private final Scanner scanner;
    private final DateTimeFormatter formatter;

    public VotacaoCLI(EnqueteService enqueteService, 
                     VotoService votoService,
                     ApuracaoService apuracaoService) {
        this.enqueteService = enqueteService;
        this.votoService = votoService;
        this.apuracaoService = apuracaoService;
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }

    @Override
    public void run(String... args) {
        boolean executando = true;
        while (executando) {
            try {
                System.out.println("\nSistema de Votação");
                System.out.println("1 - Listar Enquetes");
                System.out.println("2 - Criar Enquete");
                System.out.println("3 - Votar");
                System.out.println("4 - Ver Resultados");
                System.out.println("5 - Finalizar Enquete");
                System.out.println("0 - Sair");
                System.out.print("\nEscolha uma opção: ");

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Opção inválida!");
                    continue;
                }

                int opcao;
                try {
                    opcao = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida! Digite um número.");
                    continue;
                }

                if (opcao == 0) {
                    executando = false;
                    continue;
                }

                switch (opcao) {
                    case 1:
                        listarEnquetes();
                        break;
                    case 2:
                        criarEnquete();
                        break;
                    case 3:
                        votar();
                        break;
                    case 4:
                        verResultados();
                        break;
                    case 5:
                        finalizarEnquete();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private Long lerLong(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Entrada não pode ser vazia!");
                    continue;
                }
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Entrada não pode ser vazia!");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Entrada não pode ser vazia!");
        }
    }

    private void listarEnquetes() {
        List<Enquete> enquetes = enqueteService.listarEnquetes();
        if (enquetes.isEmpty()) {
            System.out.println("Nenhuma enquete encontrada.");
            return;
        }

        System.out.println("\nEnquetes disponíveis:");
        enquetes.forEach(enquete -> {
            System.out.printf("\nID: %d\n", enquete.getId());
            System.out.printf("Título: %s\n", enquete.getTitulo());
            System.out.printf("Descrição: %s\n", enquete.getDescricao());
            System.out.printf("Início: %s\n", enquete.getDataInicio().format(formatter));
            System.out.printf("Fim: %s\n", enquete.getDataFim().format(formatter));
            System.out.printf("Status: %s\n", enquete.isAtiva() ? "Ativa" : "Encerrada");
            System.out.println("Opções de voto:");
            enquete.getOpcoes().forEach(opcao -> 
                System.out.printf("- %s\n", opcao.getDescricao()));
        });
    }

    private void criarEnquete() {
        System.out.println("\nCriar nova enquete:");
        
        String titulo = lerTexto("Título: ");
        String descricao = lerTexto("Descrição: ");
        
        int diasDuracao = lerInteiro("Dias de duração: ");
        if (diasDuracao <= 0) {
            throw new RuntimeException("A duração deve ser maior que zero");
        }

        Enquete enquete = new Enquete();
        enquete.setTitulo(titulo);
        enquete.setDescricao(descricao);
        enquete.setDataInicio(LocalDateTime.now());
        enquete.setDataFim(LocalDateTime.now().plusDays(diasDuracao));
        enquete.setAtiva(true);

        int numOpcoes = lerInteiro("Quantas opções de voto? ");
        if (numOpcoes < 2) {
            throw new RuntimeException("A enquete deve ter pelo menos 2 opções de voto");
        }

        for (int i = 0; i < numOpcoes; i++) {
            String descricaoOpcao = lerTexto(String.format("Opção %d: ", i + 1));
            
            OpcaoVoto opcao = new OpcaoVoto();
            opcao.setDescricao(descricaoOpcao);
            opcao.setEnquete(enquete);
            enquete.getOpcoes().add(opcao);
        }

        Enquete enqueteSalva = enqueteService.criarEnquete(enquete);
        System.out.printf("\nEnquete criada com sucesso! ID: %d\n", enqueteSalva.getId());
    }

    private void votar() {
        System.out.println("\nRegistrar voto:");
        
        Long enqueteId = lerLong("ID da enquete: ");

        Enquete enquete = enqueteService.buscarEnquete(enqueteId);
        
        if (!enquete.isAtiva()) {
            throw new RuntimeException("Esta enquete está encerrada");
        }
        
        if (LocalDateTime.now().isAfter(enquete.getDataFim())) {
            throw new RuntimeException("Esta enquete já expirou");
        }
        
        System.out.println("\nOpções disponíveis:");
        enquete.getOpcoes().forEach(opcao -> 
            System.out.printf("%d - %s\n", opcao.getId(), opcao.getDescricao()));
        
        Long opcaoId = lerLong("\nEscolha a opção (ID): ");
        
        boolean opcaoValida = enquete.getOpcoes().stream()
                .anyMatch(opcao -> opcao.getId().equals(opcaoId));
        if (!opcaoValida) {
            throw new RuntimeException("Opção inválida");
        }
        
        String cpf = lerTexto("CPF do votante: ");

        votoService.registrarVoto(enqueteId, opcaoId, cpf);
        System.out.println("\nVoto registrado com sucesso!");
    }

    private void verResultados() {
        Long enqueteId = lerLong("Digite o ID da enquete: ");

        System.out.println("\nEscolha o tipo de apuração:");
        for (TipoApuracao tipo : TipoApuracao.values()) {
            System.out.printf("%d - %s\n", tipo.ordinal() + 1, tipo.getDescricao());
        }
        
        int opcao = lerInteiro("\nOpção: ");
        
        if (opcao < 1 || opcao > TipoApuracao.values().length) {
            System.out.println("Opção inválida!");
            return;
        }
        
        TipoApuracao tipoApuracao = TipoApuracao.values()[opcao - 1];

        try {
            Map<OpcaoVoto, Long> resultados = apuracaoService.apurarResultados(enqueteId, tipoApuracao);
            Enquete enquete = enqueteService.buscarEnquete(enqueteId);
            
            System.out.printf("\nResultados da Enquete: %s\n", enquete.getTitulo());
            System.out.printf("Tipo de Apuração: %s\n", tipoApuracao.getDescricao());
            System.out.println("----------------------------------------");
            
            resultados.forEach((opcaoVoto, valor) -> {
                if (tipoApuracao == TipoApuracao.PORCENTAGEM) {
                    System.out.printf("%s: %d%%\n", opcaoVoto.getDescricao(), valor);
                } else {
                    System.out.printf("%s: %d votos\n", opcaoVoto.getDescricao(), valor);
                }
            });
            System.out.println("----------------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao apurar resultados: " + e.getMessage());
        }
    }

    private void finalizarEnquete() {
        Long enqueteId = lerLong("Digite o ID da enquete: ");

        try {
            Enquete enquete = enqueteService.finalizarEnquete(enqueteId);
            System.out.printf("\nEnquete '%s' finalizada com sucesso!\n", enquete.getTitulo());
        } catch (Exception e) {
            System.out.println("Erro ao finalizar enquete: " + e.getMessage());
        }
    }
} 