package com.university.equationsapp.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.equationsapp.web.dto.CreateProblemDTO;

public class NumVariablesValidator implements ConstraintValidator<NumVariables, CreateProblemDTO> {

	public void initialize(NumVariables annotation) {
// no-op - no initialization needed
	}

	public boolean isValid(CreateProblemDTO createProblemDTO, ConstraintValidatorContext context) {
		boolean isValid = true;

		if (createProblemDTO.getNumVariables() == 3) {
			if ((createProblemDTO.getEquation3() == null) || (createProblemDTO.getEquation3().trim().length() == 0)
					|| (createProblemDTO.getVariableZ() == null)
					|| (createProblemDTO.getVariableZ().trim().length() == 0)) {
				isValid = false;
			}
		}
		return isValid;
	}
}
