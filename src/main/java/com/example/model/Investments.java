package com.example.model;

import java.util.List;

public class Investments {
	
	private List<Investment> investments;
	private double totalWorth;
	
	public Investments(List<Investment> investments) {
		super();
		this.investments = investments;
		this.totalWorth = calculate();
	}

	private double calculate() {
		double total=0;

		for (Investment investment : investments) {
			if(investment!=null){
			
			total+=investment.getValue();
			}
		
		}
		return total;
	}

	public List<Investment> getInvestments() {
		return investments;
	}

	public void setInvestments(List<Investment> investments) {
		this.investments = investments;
	}

	public double getTotalWorth() {
		return totalWorth;
	}

	public void setTotalWorth(double totalWorth) {
		this.totalWorth = totalWorth;
	}
		
}
