package br.edu.ifpr.cars.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TravelRequestDTO {

    @NotBlank
    private String origem;

    @NotBlank
    private String destino;

    private Long passengerId;
}
