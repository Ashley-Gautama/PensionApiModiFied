package com.example.model;

import java.util.List;

import lombok.Data;

@Data
public class Investments {
	private List<Investment> investments;
	private double totalWorth;

	public Investments(List<Investment> investments) {
		super();
		this.investments = investments;
		this.totalWorth = calculate();
	}

	private double calculate() {
		double total = 0;
		for (Investment investment : investments) {
			if (investment != null) {
				total += investment.getValue();
			}
		}
		return total;
	}
}