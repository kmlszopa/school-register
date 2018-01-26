package pl.kamilszopa.model;

import java.util.HashSet;

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
		this.surName = surName;
		this.firstName = firstName;
		this.emailAdress = emailAdress;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
		this.active = 1;
		this.roles = new HashSet<Role>();
	}

}
