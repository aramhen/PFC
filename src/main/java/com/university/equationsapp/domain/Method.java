package com.university.equationsapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the methods database table.
 * 
 */
@Entity
@Table(name="methods")
@NamedQuery(name="Method.findAll", query="SELECT m FROM Method m")
public class Method implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMethods;

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