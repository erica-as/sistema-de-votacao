package com.pucminas.projeto.software.votacao.config;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import com.pucminas.projeto.software.votacao.model.Voto;
import com.pucminas.projeto.software.votacao.repository.EnqueteRepository;
import com.pucminas.projeto.software.votacao.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    @Order(1)
    public CommandLineRunner initData(EnqueteRepository enqueteRepository, VotoRepository votoRepository) {
        return args -> {
            logger.info("Iniciando população do banco de dados...");
            
            // Enquete 1: Eleições 2026
            logger.info("Criando enquete: Eleições 2026");
            Enquete eleicoes = criarEnquete(
                "Eleições 2026",
                "Votação para presidente da república",
                30,
                Arrays.asList("Lula", "Bolsonaro", "Ciro Gomes", "Simone Tebet")
            );
            enqueteRepository.save(eleicoes);
            
            // Adicionar votos para eleições
            logger.info("Adicionando votos para Eleições 2026");
            adicionarVotos(eleicoes, votoRepository, Arrays.asList(
                "123.456.789-00", "987.654.321-00", "111.222.333-44",
                "555.666.777-88", "999.888.777-66", "444.333.222-11"
            ));

            // Enquete 2: Melhor Linguagem de Programação
            logger.info("Criando enquete: Melhor Linguagem de Programação 2024");
            Enquete linguagens = criarEnquete(
                "Melhor Linguagem de Programação 2024",
                "Vote na sua linguagem de programação favorita",
                15,
                Arrays.asList("Java", "Python", "JavaScript", "C#", "Go", "Rust")
            );
            enqueteRepository.save(linguagens);
            
            // Adicionar votos para linguagens
            logger.info("Adicionando votos para Melhor Linguagem de Programação 2024");
            adicionarVotos(linguagens, votoRepository, Arrays.asList(
                "123.456.789-00", "987.654.321-00", "111.222.333-44",
                "555.666.777-88", "999.888.777-66", "444.333.222-11",
                "777.888.999-00", "222.333.444-55", "666.777.888-99"
            ));

            // Enquete 3: Melhor Time de Futebol
            logger.info("Criando enquete: Melhor Time de Futebol do Brasil");
            Enquete times = criarEnquete(
                "Melhor Time de Futebol do Brasil",
                "Vote no melhor time de futebol brasileiro",
                7,
                Arrays.asList("Flamengo", "Palmeiras", "São Paulo", "Corinthians", "Grêmio", "Internacional")
            );
            enqueteRepository.save(times);
            
            // Adicionar votos para times
            logger.info("Adicionando votos para Melhor Time de Futebol do Brasil");
            adicionarVotos(times, votoRepository, Arrays.asList(
                "123.456.789-00", "987.654.321-00", "111.222.333-44",
                "555.666.777-88", "999.888.777-66", "444.333.222-11",
                "777.888.999-00", "222.333.444-55", "666.777.888-99",
                "333.444.555-66", "888.999.000-11", "444.555.666-77"
            ));
            
            logger.info("População do banco de dados concluída com sucesso!");
        };
    }

    private Enquete criarEnquete(String titulo, String descricao, int diasDuracao, List<String> opcoes) {
        Enquete enquete = new Enquete();
        enquete.setTitulo(titulo);
        enquete.setDescricao(descricao);
        enquete.setDataInicio(LocalDateTime.now());
        enquete.setDataFim(LocalDateTime.now().plusDays(diasDuracao));
        enquete.setAtiva(true);

        opcoes.forEach(descricaoOpcao -> {
            OpcaoVoto opcao = new OpcaoVoto();
            opcao.setDescricao(descricaoOpcao);
            opcao.setEnquete(enquete);
            enquete.getOpcoes().add(opcao);
        });

        return enquete;
    }

    private void adicionarVotos(Enquete enquete, VotoRepository votoRepository, List<String> cpfs) {
        List<OpcaoVoto> opcoes = enquete.getOpcoes();
        
        for (int i = 0; i < cpfs.size(); i++) {
            String cpf = cpfs.get(i);
            OpcaoVoto opcao = opcoes.get(i % opcoes.size());
            
            Voto voto = new Voto();
            voto.setEnquete(enquete);
            voto.setOpcaoVoto(opcao);
            voto.setCpfVotante(cpf);
            voto.setDataVoto(LocalDateTime.now().minusHours(i)); // Votos em horários diferentes
            
            votoRepository.save(voto);
        }
    }
} 