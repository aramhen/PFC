package com.university.equationsapp.web;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.university.equationsapp.web.constants.WebConstants;

public class CreateProblem {

	@NotNull
	private int idMethod;

	//Las variables las limito de 2 a 3, asi no se puede introducir un valor no permitido editanto el html del formulario
	@NotNull
	@Range(min = WebConstants.NUM_VARIABLES_MIN, max = WebConstants.NUM_VARIABLES_MAX, message = "{createproblem.validation.numvariables}")
	private int numVariables;

	@NotNull
	private String equation1;

	@NotNull
	private String equation2;

	@NotNull
	private String equation3;

	private String variableX;
	private String variableY;
	private String variableZ;

	private boolean solutionCheck;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	//Eso lo he usado para poder ponerle formato a la fecha al mostrarse en el input por defecto, si diero error de libreria joda hay que incluir
	/*
	 * <dependency> <groupid>joda-time</groupid> <artifactid>joda-time</artifactid> <version>1.6.2</version>
	 * <scope>runtime</scope> </dependency>
	 */
	private Date initDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	private boolean uniqueAnswer;

	//Hay que ver como consigo esto, de sesion, cookies o lo que sea
	private int idTeacher;

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
		this.equation1 = equation1;
	}

	public String getEquation2() {
		return equation2;
	}

	public void setEquation2(String equation2) {
		this.equation2 = equation2;
	}

	public String getEquation3() {
		return equation3;
	}

	public void setEquation3(String equation3) {
		this.equation3 = equation3;
	}

	public String getVariableX() {
		return variableX;
	}

	public void setVariableX(String variableX) {
		this.variableX = variableX;
	}

	public String getVariableY() {
		return variableY;
	}

	public void setVariableY(String variableY) {
		this.variableY = variableY;
	}

	public String getVariableZ() {
		return variableZ;
	}

	public void setVariableZ(String variableZ) {
		this.variableZ = variableZ;
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

}
