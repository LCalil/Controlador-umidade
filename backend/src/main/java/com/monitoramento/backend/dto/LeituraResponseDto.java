package com.monitoramento.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraResponseDto {
    private Long id;
    private Double valorUmidade;
    private LocalDateTime dataLeitura;
    private Long plantaId;
    private String nomePlanta;
    private boolean alertaAtivo; // Indica se a umidade está abaixo do ideal
}
