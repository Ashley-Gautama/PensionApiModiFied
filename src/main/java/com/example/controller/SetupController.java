package com.example.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Account;
import com.example.model.Address;
import com.example.model.BankAccountHolder;
import com.example.model.Investment;
import com.example.service.AccountService;
import com.example.service.AddressService;
import com.example.service.BankAccountHolderService;
import com.example.service.InvestmentService;

@Controller
@Path("/")
public class SetupController {

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private AccountService accountService;
    @Autowired
    private BankAccountHolderService bankaccountholderService;
    
    @Autowired
    private InvestmentService investmentService;

    @GET
    @Produces("text/plain")
    @Path("dbsetup")

    public void setUp() {
    	dbSetup();
    }
    
    
    @GET
    @Produces("application/json")
    @Path("/")
    public BankAccountHolder retrieveHolder(){
    	return bankaccountholderService.get(3);
    }
    
    
  
    @PostConstruct

    private void dbSetup() {
    	
    	System.out.println("hello");
    	
    	Account a=new Account(94356);
    	Address add=new Address("jokerstreet", "Gotham", "1276 hc");
    	BankAccountHolder h=new BankAccountHolder(4,"Bruce", "Wayne", "BW@Wayneenterprises.com", add, a);
    	
    	Account acc=new Account(2967);
    	Address addrr=new Address("Baynestreet", "Gotham", "1274 rk");
    	BankAccountHolder ho=new BankAccountHolder(3,"The", "Batman", "BW@Wayneenterprises.com", addrr, acc);
    	
    	
    	
    	accountService.add(a);
    	accountService.add(acc);

    	addressService.add(add);
    	addressService.add(addrr);

    	
    	bankaccountholderService.add(h);
    	bankaccountholderService.add(ho);

       
    }
}
