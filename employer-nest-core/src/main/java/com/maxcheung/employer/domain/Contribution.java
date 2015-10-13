package com.maxcheung.employer.domain;

public class Contribution {

	private String surname;
	private String employerReference;

	private String contribType;

	public Contribution()
	{
	}

	public Contribution(String surname, String employerReference, String contribType)
	{
		this.surname = surname;
		this.employerReference = employerReference;
		this.contribType = contribType;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmployerReference() {
		return employerReference;
	}

	public void setEmployerReference(String employerReference) {
		this.employerReference = employerReference;
	}

	public String getContribType() {
		return contribType;
	}

	public void setContribType(String contribType) {
		this.contribType = contribType;
	}
	


	
	
	
}
