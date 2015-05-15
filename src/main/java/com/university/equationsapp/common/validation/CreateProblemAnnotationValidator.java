package com.university.equationsapp.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.university.equationsapp.web.dto.CreateProblemDTO;

public class CreateProblemAnnotationValidator implements ConstraintValidator<CreateProblemAnnotation, CreateProblemDTO> {

	public void initialize(CreateProblemAnnotation annotation) {
		//No initialization needed
	}

	public boolean isValid(CreateProblemDTO createProblemDTO, ConstraintValidatorContext context) {
		boolean isValid = true;

		if (createProblemDTO.getNumVariables() == 1) {
			if ((createProblemDTO.getEquation1() == null) || (createProblemDTO.getEquation1().trim().length() == 0)) {
				isValid = false;
				context.buildConstraintViolationWithTemplate("{createproblem.validation.equations.1required}")
						.addPropertyNode("numVariables").addConstraintViolation();
			}
			if (createProblemDTO.isSolutionCheck()) {
				if ((createProblemDTO.getVariableX() == null) || (createProblemDTO.getVariableX().trim().length() == 0)) {
					isValid = false;
					context.buildConstraintViolationWithTemplate("{createproblem.validation.solution.1required}")
							.addPropertyNode("solutionCheck").addConstraintViolation();
				}
			}
		}

		if (createProblemDTO.getNumVariables() == 2) {
			if ((createProblemDTO.getEquation1() == null) || (createProblemDTO.getEquation1().trim().length() == 0)
					|| (createProblemDTO.getEquation2() == null)
					|| (createProblemDTO.getEquation2().trim().length() == 0)) {
				isValid = false;
				context.buildConstraintViolationWithTemplate("{createproblem.validation.equations.2required}")
						.addPropertyNode("numVariables").addConstraintViolation();
			}
			if (createProblemDTO.isSolutionCheck()) {
				if ((createProblemDTO.getVariableX() == null) || (createProblemDTO.getVariableX().trim().length() == 0)
						|| (createProblemDTO.getVariableY() == null)
						|| (createProblemDTO.getVariableY().trim().length() == 0)) {
					isValid = false;
					context.buildConstraintViolationWithTemplate("{createproblem.validation.solution.2required}")
							.addPropertyNode("solutionCheck").addConstraintViolation();
				}
			}
		}

		if (createProblemDTO.getNumVariables() == 3) {
			if ((createProblemDTO.getEquation1() == null) || (createProblemDTO.getEquation1().trim().length() == 0)
					|| (createProblemDTO.getEquation2() == null)
					|| (createProblemDTO.getEquation2().trim().length() == 0)
					|| (createProblemDTO.getEquation3() == null)
					|| (createProblemDTO.getEquation3().trim().length() == 0)) {
				isValid = false;
				context.buildConstraintViolationWithTemplate("{createproblem.validation.equations.3required}")
						.addPropertyNode("numVariables").addConstraintViolation();
			}
			if (createProblemDTO.isSolutionCheck()) {
				if ((createProblemDTO.getVariableX() == null) || (createProblemDTO.getVariableX().trim().length() == 0)
						|| (createProblemDTO.getVariableY() == null)
						|| (createProblemDTO.getVariableY().trim().length() == 0)
						|| (createProblemDTO.getVariableZ() == null)
						|| (createProblemDTO.getVariableZ().trim().length() == 0)) {
					isValid = false;
					context.buildConstraintViolationWithTemplate("{createproblem.validation.solution.3required}")
							.addPropertyNode("solutionCheck").addConstraintViolation();
				}
			}
		}

		if ((createProblemDTO.getInitDate() != null) && (createProblemDTO.getEndDate() != null)) {
			if (createProblemDTO.getInitDate().after(createProblemDTO.getEndDate())) {
				isValid = false;
				context.buildConstraintViolationWithTemplate("{createproblem.validation.dates}")
						.addPropertyNode("initDate").addConstraintViolation();
			}
		}

		if (!isValid) {
			context.disableDefaultConstraintViolation();
		}

		return isValid;
	}
}
