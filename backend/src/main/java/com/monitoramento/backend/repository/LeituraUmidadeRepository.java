package com.monitoramento.backend.repository;

import com.monitoramento.backend.model.LeituraUmidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeituraUmidadeRepository extends JpaRepository<LeituraUmidade, Long> {
    List<LeituraUmidade> findByPlantaIdOrderByDataLeituraDesc(Long plantaId);
}
