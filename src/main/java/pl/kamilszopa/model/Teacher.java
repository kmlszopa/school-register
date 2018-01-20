package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Teacher extends Parent {
	protected String surName;
	protected String firstName;
	protected String emailAdress;
	protected String phoneNumber;
	@OneToOne
	private Subject subject;

	public Teacher() {
	}

	public Teacher(String surName, String firstName, String emailAdress, String phoneNumber, Subject subject) {
		this.surName = surName;
		this.firstName = firstName;
		this.emailAdress = emailAdress;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
	}
}
