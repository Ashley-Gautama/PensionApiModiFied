package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PensionService {
	@Autowired
	private InvestmentService iService;

	public PensionService() {
		super();
			}
	
	
	
	
	
public double calculateInvestmentPart(double investmentValue, Date pensionDate,double percentage) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date firstDate=null;
	try {
		firstDate = sdf.parse(LocalDate.now().toString());

	} catch (ParseException e) {
				e.printStackTrace();
	}
 
    long diffInMillies = Math.abs(pensionDate.getTime() - firstDate.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    final int yearInDays=365;
    double total=0;
    
    
    while(diff > yearInDays){
    	percentage=calculateFigure(percentage, yearInDays);
    	investmentValue=calculateFunds(investmentValue,percentage);	
    	diff=diff-yearInDays;
  	
    }
    
    percentage=calculateFigure(percentage, (int)diff);
  
    investmentValue=calculateFunds(investmentValue,percentage);
	
	
	return investmentValue;
	}

private double calculateFunds(double investmentValue, double percentage) {
return investmentValue * (1+(percentage/100));
}

public double calculateSalaryPartPension(double partimeFactor, double franchise, double premium, Date date, double salary){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date firstDate=null;
	try {
		firstDate = sdf.parse(LocalDate.now().toString());

	} catch (ParseException e) {
				e.printStackTrace();
	}
	
	
 
    long diffInMillies = Math.abs(date.getTime() - firstDate.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    final int yearInDays=365;
    double total=0;
    
    
    while(diff > yearInDays){
    	salary=calculateFigure(salary, yearInDays);
    	 franchise=calculateFigure(franchise, yearInDays);
    	premium=calculateFigure(premium, yearInDays);
   
    	total+=calculateFunds(partimeFactor,franchise, premium,salary);
    	
    	diff=diff-yearInDays;
  	
    }
    
    salary=calculateFigure(salary, (int)diff);
	franchise=calculateFigure(franchise, (int)diff);
	premium=calculateFigure(premium, (int)diff);
	
	total+=calculateFunds(partimeFactor,franchise, premium,salary);
	
	
	return total;
	
}

private double calculateFunds(double partimeFactor, double franchiso, double premio, double salarya) {
	double premia=1+(premio/100);
	return ((salarya-franchiso)*partimeFactor)*premia;
}

private double calculateFigure(double figure, int days) {
	
	return (figure/365)*days;
	
}
}




	


