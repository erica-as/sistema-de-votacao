package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@ApuracaoStrategyType(TipoApuracao.PORCENTAGEM)
public class ApuracaoPorcentagemStrategy implements ApuracaoStrategy {
    @Override
    public Map<OpcaoVoto, Long> apurar(Enquete enquete) {
        Map<OpcaoVoto, Long> resultado = new HashMap<>();
        long totalVotos = enquete.getVotos().size();
        
        if (totalVotos == 0) {
            enquete.getOpcoes().forEach(opcao -> resultado.put(opcao, 0L));
            return resultado;
        }
        
        enquete.getOpcoes().forEach(opcao -> {
            long votos = enquete.getVotos().stream()
                    .filter(voto -> voto.getOpcaoVoto().getId().equals(opcao.getId()))
                    .count();
            long porcentagem = (votos * 100) / totalVotos;
            resultado.put(opcao, porcentagem);
        });
        
        return resultado;
    }
} 