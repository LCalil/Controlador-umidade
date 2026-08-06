package com.monitoramento.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "leitura_umidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraUmidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valorUmidade;

    @Column(nullable = false)
    private LocalDateTime dataLeitura;

    @ManyToOne
    @JoinColumn(name = "planta_id", nullable = false)
    private Planta planta;
}
