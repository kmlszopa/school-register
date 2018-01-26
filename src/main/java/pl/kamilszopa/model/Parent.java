package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Parent {
	@Id
	@GeneratedValue
	protected Long id;
	protected String surName;
	protected String firstName;
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	protected String emailAdress;
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	protected String password;
	protected String phoneNumber;

	public Parent() {
	}

	public Parent(String surName, String firstName, String emailAdress, String password, String phoneNumber) {
		this.surName = surName;
		this.firstName = firstName;
		this.emailAdress = emailAdress;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	
}
