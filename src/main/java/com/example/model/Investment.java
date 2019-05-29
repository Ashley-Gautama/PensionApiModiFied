package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Investment {
	private String name;
	private double value;

	public Investment() {
	}

}
