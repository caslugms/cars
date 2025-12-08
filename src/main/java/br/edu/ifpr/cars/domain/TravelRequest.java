package br.edu.ifpr.cars.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class TravelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Origem é obrigatória")
    private String origem;

    @NotBlank(message = "Destino é obrigatório")
    private String destino;

    @Enumerated(EnumType.STRING)
    private TravelRequestStatus status = TravelRequestStatus.CREATED;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    private Long driverId;
}