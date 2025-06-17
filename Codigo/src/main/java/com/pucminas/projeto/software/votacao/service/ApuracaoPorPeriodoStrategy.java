package com.pucminas.projeto.software.votacao.service;

import com.pucminas.projeto.software.votacao.model.Enquete;
import com.pucminas.projeto.software.votacao.model.OpcaoVoto;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@ApuracaoStrategyType(TipoApuracao.PERIODO)
public class ApuracaoPorPeriodoStrategy implements ApuracaoStrategy {
    @Override
    public Map<OpcaoVoto, Long> apurar(Enquete enquete) {
        Map<OpcaoVoto, Long> resultado = new HashMap<>();
        LocalDateTime agora = LocalDateTime.now();
        
        // Considera apenas votos do último dia
        LocalDateTime inicioPeriodo = agora.minusDays(1);
        
        enquete.getOpcoes().forEach(opcao -> {
            long votos = enquete.getVotos().stream()
                    .filter(voto -> voto.getOpcaoVoto().getId().equals(opcao.getId()))
                    .filter(voto -> voto.getDataVoto().isAfter(inicioPeriodo))
                    .count();
            resultado.put(opcao, votos);
        });
        
        return resultado;
    }
} 