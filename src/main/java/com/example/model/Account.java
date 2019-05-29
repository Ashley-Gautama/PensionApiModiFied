package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Account {
	@Id
	@GeneratedValue
	private long id;
	private int bankNumber;

	public Account(int bankNumber) {
		super();
		this.id = 0;
		this.bankNumber = bankNumber;
	}

	public Account() {
		super();
	}
}
