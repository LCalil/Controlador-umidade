package com.monitoramento.backend.service;

import com.monitoramento.backend.model.Planta;
import com.monitoramento.backend.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepository;

    @Autowired
    public PlantaService(PlantaRepository plantaRepository) {
        this.plantaRepository = plantaRepository;
    }

    public List<Planta> listarTodas() {
        return plantaRepository.findAll();
    }

    public Planta salvar(Planta planta) {
        if (planta.getDataCadastro() == null) {
            planta.setDataCadastro(LocalDateTime.now());
        }
        return plantaRepository.save(planta);
    }

    public Optional<Planta> buscarPorId(Long id) {
        return plantaRepository.findById(id);
    }

    public void deletar(Long id) {
        plantaRepository.deleteById(id);
    }
}
