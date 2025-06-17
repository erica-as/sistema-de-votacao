package com.pucminas.projeto.software.votacao.config;

import com.pucminas.projeto.software.votacao.service.ApuracaoStrategy;
import com.pucminas.projeto.software.votacao.service.ApuracaoStrategyType;
import com.pucminas.projeto.software.votacao.service.TipoApuracao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class ApuracaoConfig {
    
    @Bean
    public Map<TipoApuracao, ApuracaoStrategy> estrategiasApuracao(List<ApuracaoStrategy> strategies) {
        return strategies.stream()
                .collect(Collectors.toMap(
                    strategy -> strategy.getClass().getAnnotation(ApuracaoStrategyType.class).value(),
                    Function.identity()
                ));
    }
} 