package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
@ApuracaoStrategyType(TipoApuracao.SIMPLES)
public class ApuracaoSimplesStrategy implements ApuracaoStrategy {
    @Override
    public Map<OpcaoVoto, Long> apurar(Enquete enquete) {
        Map<OpcaoVoto, Long> resultado = new HashMap<>();
        
        enquete.getOpcoes().forEach(opcao -> {
            long votos = enquete.getVotos().stream()
                    .filter(voto -> voto.getOpcaoVoto().getId().equals(opcao.getId()))
                    .count();
            resultado.put(opcao, votos);
        });
        
        return resultado;
    }
} 