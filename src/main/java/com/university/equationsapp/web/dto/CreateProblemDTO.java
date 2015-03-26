package com.university.equationsapp.web.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.university.equationsapp.web.constants.WebConstants;

public class CreateProblemDTO implements Serializable {

	static final long serialVersionUID = 1L;

	@NotNull
	@Min(value = 1, message = "{createproblem.validation.idmethod}")
	private int idMethod;

	//TODO ARH Las variables las limito de 2 a 3, asi no se puede introducir un valor no permitido editanto el html del formulario
	@NotNull
	@Range(min = WebConstants.NUM_VARIABLES_MIN, max = WebConstants.NUM_VARIABLES_MAX, message = "{createproblem.validation.numvariables}")
	private int numVariables;

	@NotBlank
	@Size(min = WebConstants.EQUATION_LENGTH_MIN, max = WebConstants.EQUATION_LENGTH_MAX, message = "{createproblem.validation.equation.size}")
	private String equation1;
	@NotBlank
	@Size(min = WebConstants.EQUATION_LENGTH_MIN, max = WebConstants.EQUATION_LENGTH_MAX, message = "{createproblem.validation.equation.size}")
	private String equation2;
	@Size(min = WebConstants.EQUATION_LENGTH_MIN, max = WebConstants.EQUATION_LENGTH_MAX, message = "{createproblem.validation.equation.size}")
	private String equation3;

	@NotBlank
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableX;
	@NotBlank
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableY;
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableZ;

	private boolean solutionCheck;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//Eso lo he usado para poder ponerle formato a la fecha al mostrarse en el input por defecto, si diera error de libreria joda hay que incluir
	/*
	 * <dependency> <groupid>joda-time</groupid> <artifactid>joda-time</artifactid> <version>1.6.2</version>
	 * <scope>runtime</scope> </dependency>
	 */
	private Date initDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	private boolean uniqueAnswer;

	@NotNull
	//TODO ARH Hay que ver como consigo esto, de sesion, cookies o lo que sea
	private int idTeacher;

	@NotBlank(message = "{createproblem.validation.title}")
	@Size(min = WebConstants.TITLE_LENGTH_MIN, max = WebConstants.TITLE_LENGTH_MAX, message = "{createproblem.validation.title.size}")
	private String title;

	public int getIdMethod() {
		return idMethod;
	}

	public void setIdMethod(int idMethod) {
		this.idMethod = idMethod;
	}

	public int getNumVariables() {
		return numVariables;
	}

	public void setNumVariables(int numVariables) {
		this.numVariables = numVariables;
	}

	public String getEquation1() {
		return equation1;
	}

	public void setEquation1(String equation1) {
		this.equation1 = equation1.trim();
	}

	public String getEquation2() {
		return equation2;
	}

	public void setEquation2(String equation2) {
		this.equation2 = equation2.trim();
	}

	public String getEquation3() {
		return equation3;
	}

	public void setEquation3(String equation3) {
		this.equation3 = equation3.trim();
	}

	public String getVariableX() {
		return variableX;
	}

	public void setVariableX(String variableX) {
		this.variableX = variableX.trim();
	}

	public String getVariableY() {
		return variableY;
	}

	public void setVariableY(String variableY) {
		this.variableY = variableY.trim();
	}

	public String getVariableZ() {
		return variableZ;
	}

	public void setVariableZ(String variableZ) {
		this.variableZ = variableZ.trim();
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isUniqueAnswer() {
		return uniqueAnswer;
	}

	public void setUniqueAnswer(boolean uniqueAnswer) {
		this.uniqueAnswer = uniqueAnswer;
	}

	public boolean isSolutionCheck() {
		return solutionCheck;
	}

	public void setSolutionCheck(boolean solutionCheck) {
		this.solutionCheck = solutionCheck;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

}
