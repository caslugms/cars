package br.edu.ifpr.cars.validate;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AnoFabricacaoValidator implements ConstraintValidator<AnoFabricacaoValido, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        int atual = LocalDate.now().getYear();

        return value >= 1886 && value <= atual;
    }
}
