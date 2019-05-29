package com.example.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.model.Account;
import com.example.model.Address;
import com.example.model.BankAccountHolder;
import com.example.service.BankAccountHolderService;

@Controller
@Path("/")
public class SetupController {
	@Autowired
	private BankAccountHolderService bankaccountholderService;

	@Autowired
	@GET
	@Produces("text/plain")
	@Path("dbsetup")
	public void setUp() {
		dbSetup();
	}

	@GET
	@Produces("application/json")
	@Path("/")
	public BankAccountHolder retrieveHolder() {
		return bankaccountholderService.findById(3).get();
	}

	private void dbSetup() {
		Account a = new Account(94356);
		Address add = new Address("jokerstreet", "Gotham", "1276 hc");
		BankAccountHolder h = new BankAccountHolder(4, "Bruce", "Wayne", "BW@Wayneenterprises.com", add, a);
		Account acc = new Account(2967);
		Address addrr = new Address("Baynestreet", "Gotham", "1274 rk");
		BankAccountHolder ho = new BankAccountHolder(3, "The", "Batman", "BW@Wayneenterprises.com", addrr, acc);
		bankaccountholderService.add(h);
		bankaccountholderService.add(ho);
	}
}
