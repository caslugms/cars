package br.edu.ifpr.cars.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TravelRequestDTO {

    @NotBlank(message = "Origem é obrigatória.")
    private String origem;

    @NotBlank(message = "Destino é obrigatório.")
    private String destino;

    @NotNull(message = "O ID do passageiro é obrigatório.")
    private Long passengerId;
}
