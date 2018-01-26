package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Teacher extends Parent {
	@OneToOne
	private Subject subject;

	public Teacher() {
	}

	public Teacher(String surName, String firstName, String emailAdress, String password, String phoneNumber, Subject subject ) {
		super.surName = surName;
		this.firstName = firstName;
		this.emailAdress = emailAdress;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
	}

}
