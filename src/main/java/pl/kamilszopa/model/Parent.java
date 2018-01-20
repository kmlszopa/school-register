package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Parent {
	@Id
	@GeneratedValue
	private Long id;
	private String surName;
	private String firstName;
	private String emailAdress;
	private String phoneNumber;

	public Parent() {
	}

	public Parent(String surName, String firstName, String emailAdress, String phoneNumber) {
		this.surName = surName;
		this.firstName = firstName;
		this.emailAdress = emailAdress;
		this.phoneNumber = phoneNumber;
	}
}
