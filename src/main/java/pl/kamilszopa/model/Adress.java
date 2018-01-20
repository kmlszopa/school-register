package pl.kamilszopa.model;

import lombok.Data;

@Data
public class Adress {
	private String city;
	private String postalCode;
	private String street;
	private String houseNumber;

	public Adress(String city, String postalCode, String street, String houseNumber) {
		super();
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public Adress() {
	}

}
