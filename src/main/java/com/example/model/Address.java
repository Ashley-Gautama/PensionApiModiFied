package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Address {
	@Id
	@GeneratedValue
	private long id;
	private String street;
	private String city;
	private String zipcode;

	public Address() {
		super();
	}

	public Address(String street, String city, String zipcode) {
		super();
		this.id = 0;
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
	}
}
