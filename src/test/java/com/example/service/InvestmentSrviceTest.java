package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Investment;
import com.example.model.Investments;

@RunWith(SpringRunner.class)
public class InvestmentSrviceTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	@Mock
	private RestTemplate template;
	@InjectMocks
	private InvestmentService service;

	public InvestmentSrviceTest() {
		super();
	}

	@Test
	public void retrieveInvestmentsAccount2967() {
		Investments investments = new Investments(new ArrayList<Investment>(Arrays.asList(new Investment("LG", 3987.0),
				new Investment("Honda", 7898.0), new Investment("Lenovo", 4522.0))));
		when(template.getForEntity("http://localhost:8080/mockAPI/getInvestmentsByAccountId/" + 2967,
				Investments.class)).thenReturn(new ResponseEntity<Investments>(investments, HttpStatus.OK));
		Investments investmentss = service.retrieveInvestments(2967);
		Assert.assertEquals(investments, investmentss);
	}

	@Test
	public void unAbleRetrieveNonExistingAccount() {
		Investments investments = new Investments(new ArrayList<Investment>(Arrays.asList(new Investment("LG", 3987.0),
				new Investment("Honda", 7898.0), new Investment("Lenovo", 4522.0))));
		ResponseEntity<Investments> o = null;
		when(template.getForEntity("http://localhost:8080/mockAPI/getInvestmentsByAccountId/" + 112, Investments.class))
				.thenReturn(o = new ResponseEntity<Investments>(investments, HttpStatus.OK));
		Investments investmentss = service.retrieveInvestments(112);
		Assert.assertNotEquals(investments, investmentss);
	}
}
