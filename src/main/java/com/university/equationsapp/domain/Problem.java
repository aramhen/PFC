package com.university.equationsapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the problems database table.
 * 
 */
@Entity
@Table(name = "problems")
public class Problem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idProblems")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProblems;

	@Column(name = "equations", length = 45)
	private String equations;

	@Column(name = "endDate")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "initDate")
	@Temporal(TemporalType.DATE)
	private Date initDate;

	@Column(name = "numVariables")
	private int numVariables;

	@Column(name = "solution", length = 45)
	private String solution;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean uniqueAnswer;

	//many-to-one association to Method
	@ManyToOne
	@JoinColumn(name = "method", referencedColumnName = "idMethods", nullable = false)
	private Method methodRef;

	//many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name = "teacher", referencedColumnName = "idTeachers", nullable = false)
	private Teacher teacherRef;

	public int getIdProblems() {
		return this.idProblems;
	}

	public void setIdProblems(int idProblems) {
		this.idProblems = idProblems;
	}

	public String getEquations() {
		return this.equations;
	}

	public void setEquations(String equations) {
		this.equations = equations;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public int getNumVariables() {
		return this.numVariables;
	}

	public void setNumVariables(int numVariables) {
		this.numVariables = numVariables;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public boolean isUniqueAnswer() {
		return uniqueAnswer;
	}

	public void setUniqueAnswer(boolean uniqueAnswer) {
		this.uniqueAnswer = uniqueAnswer;
	}

	public Method getMethodRef() {
		return methodRef;
	}

	public void setMethodRef(Method methodRef) {
		this.methodRef = methodRef;
	}

	public Teacher getTeacherRef() {
		return teacherRef;
	}

	public void setTeacherRef(Teacher teacherRef) {
		this.teacherRef = teacherRef;
	}

}