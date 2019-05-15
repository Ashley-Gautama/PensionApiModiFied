package com.example.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.model.Investments;
import com.example.model.Pension;
import com.example.service.InvestmentService;
import com.example.service.PensionService;


@Path("/investments")
public class InvestmentController {
	
	
	    @Autowired
	    private InvestmentService investmentService;
	    @Autowired
	    private PensionService pensionService;

	    

	    @Path("/{id}")
	    @GET
	    @Produces("application/json")
	    public Investments retrieve(@PathParam("id") int id) {
	    return  investmentService.retrieveInvestments(id);
	    
	    }
	    
	    
	    @Path("/pension/{salary}/{partime}/{date}/{id}")
	    @GET
	    @Produces("application/json")
	    public Pension calculate(@PathParam("salary") double salary, @PathParam("partime") double partime,@PathParam("date")String dateString,@PathParam("id") int id) {
	    double startWorth=investmentService.retrieveInvestments(id).getTotalWorth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
	    Date date=null;
		try {
			date = sdf.parse(dateString.toString());

		} catch (ParseException e) {
					e.printStackTrace();
		}
	    System.out.println(date);
	    System.out.println(dateString);

	    double salaryPart=Math.round(pensionService.calculateSalaryPartPension(partime, 15599, 5, date, salary) *100.0) / 100.0;
	    double investmentPart=Math.round(pensionService.calculateInvestmentPart(startWorth, date, 3) * 100.0) / 100.0;
	    System.out.println("invest is"+investmentPart);
	    System.out.println("slaray is"+salaryPart);

		return new Pension(date, salaryPart, investmentPart);  
	    }

}

