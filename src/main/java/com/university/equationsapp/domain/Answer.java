package com.university.equationsapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the answers database table.
 * 
 * @author Burt
 *
 */
@Entity
@Table(name = "answers")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idAnswers")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAnswers;

	@Column(name = "answerDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date answerDate;

	@Column(name = "solution", length = 45)
	private String solution;

	@Column(name = "steps")
	@Lob
	private String steps;

	//many-to-one association to Problem
	@ManyToOne
	@JoinColumn(name = "problem", referencedColumnName = "idProblems", nullable = false)
	private Problem problemRef;

	//many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "student", referencedColumnName = "idStudents", nullable = false)
	private Student studentRef;

	/**
	 * Gets the unique id of the answer (Table answers)
	 * 
	 * @return the unique id of the answer (Table answers)
	 */
	public int getIdAnswers() {
		return this.idAnswers;
	}

	/**
	 * Sets the unique id of the answer (Table answers)
	 * 
	 * @param idAnswers the unique id of the answer (Table answers)
	 */
	public void setIdAnswers(int idAnswers) {
		this.idAnswers = idAnswers;
	}

	/**
	 * Gets the date of the student's answer (Table answers)
	 * 
	 * @return the date of the student's answer (Table answers)
	 */
	public Date getAnswerDate() {
		return this.answerDate;
	}

	/**
	 * Sets the date of the student's answer (Table answers)
	 * 
	 * @param answerDate the date of the student's answer (Table answers)
	 */
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	/**
	 * Gets the student's solution (Table answers)
	 * 
	 * @return the student's solution (Table answers)
	 */
	public String getSolution() {
		return this.solution;
	}

	/**
	 * Sets the student's solution (Table answers)
	 * 
	 * @param solution the student's solution (Table answers)
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 * Gets the student's steps (Table answers)
	 * 
	 * @return the student's steps (Table answers)
	 */
	public String getSteps() {
		return this.steps;
	}

	/**
	 * Sets the student's steps (Table answers)
	 * 
	 * @param steps the student's steps (Table answers)
	 */
	public void setSteps(String steps) {
		this.steps = steps;
	}

	/**
	 * Gets the problem answered by the student (Table answers referencing table problems)
	 * 
	 * @return the problem answered by the student (Table answers referencing table problems)
	 */
	public Problem getProblemRef() {
		return this.problemRef;
	}

	/**
	 * Sets the problem answered by the student (Table answers referencing table problems)
	 * 
	 * @param problemRef the problem answered by the student (Table answers referencing table problems)
	 */
	public void setProblemRef(Problem problemRef) {
		this.problemRef = problemRef;
	}

	/**
	 * Gets the student who is answering (Table answers referencing table students)
	 * 
	 * @return the student who is answering (Table answers referencing table students)
	 */
	public Student getStudentRef() {
		return this.studentRef;
	}

	/**
	 * Sets the student who is answering (Table answers referencing table students)
	 * 
	 * @param studentRef the student who is answering (Table answers referencing table students)
	 */
	public void setStudentRef(Student studentRef) {
		this.studentRef = studentRef;
	}

	/**
	 * @return idAnswers, answerDate, solution, id of problemRef, id of studentRef
	 */
	@Override
	public String toString() {
		return "Answer [idAnswers=" + idAnswers + ", answerDate=" + answerDate + ", solution=" + solution
				+ ", problemRef id=" + problemRef.getIdProblems() + ", studentRef id=" + studentRef.getIdStudents()
				+ "]";
	}

}