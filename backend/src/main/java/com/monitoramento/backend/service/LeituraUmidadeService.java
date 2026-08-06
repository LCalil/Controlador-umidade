package com.monitoramento.backend.service;

import com.monitoramento.backend.dto.LeituraRequestDto;
import com.monitoramento.backend.dto.LeituraResponseDto;
import com.monitoramento.backend.model.LeituraUmidade;
import com.monitoramento.backend.model.Planta;
import com.monitoramento.backend.repository.LeituraUmidadeRepository;
import com.monitoramento.backend.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeituraUmidadeService {

    private final LeituraUmidadeRepository leituraUmidadeRepository;
    private final PlantaRepository plantaRepository;

    @Autowired
    public LeituraUmidadeService(LeituraUmidadeRepository leituraUmidadeRepository, PlantaRepository plantaRepository) {
        this.leituraUmidadeRepository = leituraUmidadeRepository;
        this.plantaRepository = plantaRepository;
    }

    public LeituraResponseDto registrarLeitura(LeituraRequestDto request) {
        Planta planta = plantaRepository.findById(request.getPlantaId())
                .orElseThrow(() -> new IllegalArgumentException("Planta não encontrada com ID: " + request.getPlantaId()));

        LeituraUmidade leitura = new LeituraUmidade();
        leitura.setValorUmidade(request.getValorUmidade());
        leitura.setDataLeitura(LocalDateTime.now());
        leitura.setPlanta(planta);

        LeituraUmidade leituraSalva = leituraUmidadeRepository.save(leitura);

        return converterParaDto(leituraSalva);
    }

    public List<LeituraResponseDto> obterHistoricoPorPlanta(Long plantaId) {
        if (!plantaRepository.existsById(plantaId)) {
            throw new IllegalArgumentException("Planta não encontrada com ID: " + plantaId);
        }

        return leituraUmidadeRepository.findByPlantaIdOrderByDataLeituraDesc(plantaId)
                .stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    private LeituraResponseDto converterParaDto(LeituraUmidade leitura) {
        LeituraResponseDto dto = new LeituraResponseDto();
        dto.setId(leitura.getId());
        dto.setValorUmidade(leitura.getValorUmidade());
        dto.setDataLeitura(leitura.getDataLeitura());
        dto.setPlantaId(leitura.getPlanta().getId());
        dto.setNomePlanta(leitura.getPlanta().getNome());
        
        // Alerta é ativo se a umidade atual for menor que o nível ideal da planta
        boolean alertaAtivo = leitura.getValorUmidade() < leitura.getPlanta().getNivelUmidadeIdeal();
        dto.setAlertaAtivo(alertaAtivo);

        return dto;
    }
}
