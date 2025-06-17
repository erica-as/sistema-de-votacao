package com.pucminas.projeto.software.sistemavotacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.pucminas.projeto.software.votacao")
@EnableJpaRepositories(basePackages = "com.pucminas.projeto.software.votacao.repository")
@EntityScan(basePackages = "com.pucminas.projeto.software.votacao.model")
public class SistemavotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemavotacaoApplication.class, args);
	}

}
