package com.monitoramento.backend.controller;

import com.monitoramento.backend.model.Planta;
import com.monitoramento.backend.service.PlantaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plantas")
@Tag(name = "Plantas", description = "Endpoints para gerenciamento do cadastro de plantas")
public class PlantaController {

    private final PlantaService plantaService;

    @Autowired
    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as plantas", description = "Retorna a lista de todas as plantas cadastradas no sistema.")
    public ResponseEntity<List<Planta>> listarTodas() {
        List<Planta> plantas = plantaService.listarTodas();
        return ResponseEntity.ok(plantas);
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova planta", description = "Cadastra uma nova planta especificando o nome, espécie e nível ideal de umidade.")
    public ResponseEntity<Planta> cadastrar(@RequestBody Planta planta) {
        Planta novaPlanta = plantaService.salvar(planta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPlanta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar planta por ID", description = "Retorna os detalhes de uma planta específica pelo seu identificador.")
    public ResponseEntity<Planta> buscarPorId(@PathVariable Long id) {
        return plantaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover planta", description = "Exclui o cadastro de uma planta do sistema.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (plantaService.buscarPorId(id).isPresent()) {
            plantaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
