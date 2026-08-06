package com.monitoramento.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraRequestDto {
    private Long plantaId;
    private Double valorUmidade;
}
