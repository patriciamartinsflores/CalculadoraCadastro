package br.com.unknown.cadmed.config.validacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.Class;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidacaoNomeLabDuplicado.class)
public @interface NomeLabUnico {
	String message() default "Laboratório com esse nome já existe";
	Class<?>[] groups() default{};
	public abstract Class<? extends Payload>[] payload() default{};
}
