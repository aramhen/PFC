package com.university.equationsapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the methods database table.
 * 
 */
@Entity
@Table(name = "methods")
public class Method implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idMethods")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMethods;

	@Column(name = "methodName", length = 45)
	private String methodName;

	public int getIdMethods() {
		return this.idMethods;
	}

	public void setIdMethods(int idMethods) {
		this.idMethods = idMethods;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}