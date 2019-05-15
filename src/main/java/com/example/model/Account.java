package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
private long id;
private int bankNumber;



public Account(int bankNumber) {
	super();
	this.id=0;
	this.bankNumber = bankNumber;
}



public Account() {
	super();
}



public int getBankNumber() {
	return bankNumber;
}

public void setBankNumber(int bankNumber) {
	this.bankNumber = bankNumber;
}


}





