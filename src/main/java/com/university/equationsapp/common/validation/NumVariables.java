package com.university.equationsapp.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumVariablesValidator.class)
public @interface NumVariables {
	String message() default "Rating not in range";

	// Required by validation runtime
	Class<?>[] groups() default {};

	// Required by validation runtime
	Class<? extends Payload>[] payload() default {};
}