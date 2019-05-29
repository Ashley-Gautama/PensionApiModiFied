package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

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
		return investmentService.retrieveInvestments(id);
	}

	@Path("/pension/{salary}/{partime}/{date}/{id}")
	@GET
	@Produces("application/json")
	public Pension calculate(@PathParam("salary") double salary, @PathParam("partime") double partime,
			@PathParam("date") String dateString, @PathParam("id") int id) {
		double startWorth = investmentService.retrieveInvestments(id).getTotalWorth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = null;
		Pension p = null;
		try {
			date = sdf.parse(dateString);
			double total = Math.round(pensionService.calculateTotalPension(partime, 15599, 5, date, salary, startWorth) * 100.0) / 100.0;
			return new Pension(date, total);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return p;
	}
}
