package com.example.model;

import java.util.Date;

public class Pension {
private Date pensionDate;
private double totalValue;
private double salaryPartValue;
private  double investmentPartValue;




public Pension(Date date, double salaryPartValue, double investmentPartValue) {
	super();
	this.pensionDate = date;
	this.totalValue = salaryPartValue+investmentPartValue;
	this.salaryPartValue = salaryPartValue;
	this.investmentPartValue = investmentPartValue;
}



public Pension() {
	super();
}



public Date getPensionDate() {
	return pensionDate;
}



public void setPensionDate(Date pensionDate) {
	this.pensionDate = pensionDate;
}



public double getTotalValue() {
	return totalValue;
}



public void setTotalValue(double totalValue) {
	this.totalValue = totalValue;
}



public double getSalaryPartValue() {
	return salaryPartValue;
}



public void setSalaryPartValue(double salaryPartValue) {
	this.salaryPartValue = salaryPartValue;
}



public double getInvestmentPartValue() {
	return investmentPartValue;
}



public void setInvestmentPartValue(double investmentPartValue) {
	this.investmentPartValue = investmentPartValue;
}


	
}
