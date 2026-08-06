package com.monitoramento.backend.controller;

import com.monitoramento.backend.dto.LeituraRequestDto;
import com.monitoramento.backend.dto.LeituraResponseDto;
import com.monitoramento.backend.service.LeituraUmidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leituras")
@Tag(name = "Leituras de Umidade", description = "Endpoints para registro de dados de sensores e consulta de histórico")
public class LeituraUmidadeController {

    private final LeituraUmidadeService leituraUmidadeService;

    @Autowired
    public LeituraUmidadeController(LeituraUmidadeService leituraUmidadeService) {
        this.leituraUmidadeService = leituraUmidadeService;
    }

    @PostMapping
    @Operation(summary = "Registrar leitura de umidade", description = "Recebe a leitura enviada pelo sensor (ou simulador em Python) e retorna se o alerta deve ser disparado (`alertaAtivo = true`).")
    public ResponseEntity<LeituraResponseDto> registrarLeitura(@RequestBody LeituraRequestDto request) {
        try {
            LeituraResponseDto response = leituraUmidadeService.registrarLeitura(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/planta/{plantaId}")
    @Operation(summary = "Obter histórico de umidade por planta", description = "Retorna todas as leituras registradas de uma planta específica ordenadas por data decrescente.")
    public ResponseEntity<List<LeituraResponseDto>> obterHistoricoPorPlanta(@PathVariable Long plantaId) {
        try {
            List<LeituraResponseDto> historico = leituraUmidadeService.obterHistoricoPorPlanta(plantaId);
            return ResponseEntity.ok(historico);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
