package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Pension {
	private Date pensionDate;
	private double totalValue;

	public Pension() {
		super();
	}
}
