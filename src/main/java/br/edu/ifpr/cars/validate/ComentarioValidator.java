package br.edu.ifpr.cars.validate;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComentarioValidator implements ConstraintValidator<SemPalavrasOfensivas, String> {

    private final List<String> proibidas = List.of("burro", "idiota", "lixo");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true; // campo opcional

        String texto = value.toLowerCase();

        return proibidas.stream().noneMatch(texto::contains);
    }
}
