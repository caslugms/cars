package br.edu.ifpr.cars.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PlacaValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface PlacaValida {
    String message() default "Placa inválida. Formato Mercosul incorreto.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}