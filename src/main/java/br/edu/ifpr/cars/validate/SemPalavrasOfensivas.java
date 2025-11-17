package br.edu.ifpr.cars.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ComentarioValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface SemPalavrasOfensivas {
    String message() default "Comentário contém palavras ofensivas.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
