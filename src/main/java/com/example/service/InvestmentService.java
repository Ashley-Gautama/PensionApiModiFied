package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Investment;
import com.example.model.Investments;

@Service
public class InvestmentService {
@Autowired
private RestTemplate template;


public InvestmentService() {
	super();
}


public Investments retrieveInvestments(int accountId){
	
	List<Investment> listOfInvestments=new ArrayList<>();
	
	String [] companies={"Shell BV", "Microsoft","Apple","Samsung","LG","Honda","Lenovo"};
	double [] values={1330.0,2150.0,6351.0,4350.0,3987.0,7898.0,4522.0};
	
	Investment firstInvestment=null;
	Investment secondInvestment=null;
	Investment thirdInvestment=null;
	
	if(accountId==94356){
	firstInvestment=new Investment(companies[0],values[0]);
	secondInvestment=new Investment(companies[1],values[1]);
	thirdInvestment=new Investment(companies[2],values[2]);
	}
	
	else if(accountId==2967){
		firstInvestment=new Investment(companies[4],values[4]);
		secondInvestment=new Investment(companies[5],values[5]);
		thirdInvestment=new Investment(companies[6],values[6]);
	}
	
	listOfInvestments.add(firstInvestment);
	listOfInvestments.add(secondInvestment);
	listOfInvestments.add(thirdInvestment);
	
	Investments investments=new Investments(listOfInvestments);
    ResponseEntity<Investments> respFake=null;
    
    if(listOfInvestments.get(0)!=null){
    
    
    		respFake= new ResponseEntity<Investments>(investments,HttpStatus.OK);
    //ResponseEntity<Investments> resp = 
    		//template.getForEntity("http://localhost:8080/mockAPI/getInvestmentsByAccountId/" + accountId, Investments.class);
         //resp=respFake;
         
         System.out.println(respFake.getBody().getTotalWorth());
    }
	
	else{
		respFake= new ResponseEntity<Investments>(investments,HttpStatus.NOT_FOUND);	
	}
	
    return respFake.getStatusCode() == HttpStatus.OK ? respFake.getBody() : null;}	
}

