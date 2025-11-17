package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import br.edu.ifpr.cars.validate.PlacaValida;
import br.edu.ifpr.cars.validate.CNHValida;
import br.edu.ifpr.cars.validate.AnoFabricacaoValido;
import br.edu.ifpr.cars.validate.SemPalavrasOfensivas;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "É necessário digitar um nome")
    @Size(min = 3, max = 50, message = "Tamanho deve ser entre 3 e 50 caracteres")
    @Pattern(regexp = "\\S+", message = "O nome não pode conter espaços")
    String name;

    LocalDate birthDate;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Deve ser um email válido")
    @Pattern(regexp = "\\S+", message = "O email não pode conter espaços")
    String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    @Pattern(regexp = "\\S+", message = "O CPF não pode conter espaços")
    String cpf;

    @PlacaValida
    String placa;

    @CNHValida
    String cnh;

    @AnoFabricacaoValido
    int anoCarro;

    @SemPalavrasOfensivas
    String comentario;
}
