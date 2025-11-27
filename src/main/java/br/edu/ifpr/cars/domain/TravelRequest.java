package br.edu.ifpr.cars.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class TravelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Origem é obrigatória")
    String origem;

    @NotBlank(message = "Destino é obrigatório")
    String destino;

    @Enumerated(EnumType.STRING)
    TravelRequestStatus status = TravelRequestStatus.CREATED;

    // Simples: apenas o ID do passageiro
    Long passengerId;

    // opcional: id do motorista que aceitou
    Long driverId;
}
