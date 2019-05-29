package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class PensionService {
	public PensionService() {
		super();
	}

	public double calculateTotalPension(double partimeFactor, double franchise, double premium, Date date, double salary, double currentValueInvestment) {
		long differenceInDays = returnDifferenceInDays(date);
		final int maxWeightInDays = 365;
		final int yearInDays = 365;
		double totalPension = 0;
		double premiumPaidByEmployer = 0;
		while (differenceInDays > yearInDays) {
			currentValueInvestment = currentInvestmentWorthInDays(currentValueInvestment, yearInDays, maxWeightInDays);
			premiumPaidByEmployer = premiumAddedbyEmployerPerDay(partimeFactor, franchise, premium, date, salary,
					yearInDays, maxWeightInDays);
			totalPension += estimatedPensionWorthByFormula(currentValueInvestment, premiumPaidByEmployer, 3,yearInDays,maxWeightInDays);
			differenceInDays -= yearInDays;
		}
		currentValueInvestment = currentInvestmentWorthInDays(currentValueInvestment, (int)differenceInDays, maxWeightInDays);
		premiumPaidByEmployer = premiumAddedbyEmployerPerDay(partimeFactor, franchise, premium, date, salary,
				(int)differenceInDays, maxWeightInDays);
		totalPension += estimatedPensionWorthByFormula(currentValueInvestment, premiumPaidByEmployer, 3,(int)differenceInDays,maxWeightInDays);
		return totalPension;
	}

	private double estimatedPensionWorthByFormula(double investmentPart, double salaryPart, double premium,int currentWeight, int maxWeight) {
		premium=calculateWeightedValue(premium, currentWeight, maxWeight);
		return (investmentPart + salaryPart) + ((investmentPart + (salaryPart / 2)) * (premium / 100));
	}

	private long returnDifferenceInDays(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date currentDate = null;
		long diff = 0;
		try {
			currentDate = sdf.parse(LocalDate.now().toString());
			long diffInMillies = Math.abs(date.getTime() - currentDate.getTime());
			diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}

	private double currentInvestmentWorthInDays(double investmentValue, int currentWeight, int maxWeight) {
		return calculateWeightedValue(investmentValue, (int) currentWeight, maxWeight);
	}

	private double premiumAddedbyEmployerPerDay(double partimeFactor, double franchise, double premium, Date date,
			double salary, int currentWeight, int maxWeightInDays) {
		salary = calculateWeightedValue(salary, (int) currentWeight, maxWeightInDays);
		franchise = calculateWeightedValue(franchise, (int) currentWeight, maxWeightInDays);
		premium = calculateWeightedValue(premium, (int) currentWeight, maxWeightInDays);
		return employerPremiumByFormula(partimeFactor, franchise, premium, salary);
	}

	private double employerPremiumByFormula(double partimeFactor, double franchiso, double premio, double salarya) {
		return ((salarya - franchiso) * partimeFactor) * (premio / 100);
	}

	private double calculateWeightedValue(double value, int currentWeight, int maxWeight) {
		return (value / maxWeight) * currentWeight;
	}
}
