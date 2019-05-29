package com.example.controller;

import javax.ws.rs.Path;

import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.Investment;
import com.example.model.Investments;
import com.example.service.InvestmentService;
import com.example.service.PensionService;

@Path("/investments")
@RunWith(SpringRunner.class)
public class InvestmentControllerTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	@InjectMocks
	private InvestmentController iController;
	@Mock
	private InvestmentService investmentService;
	@Mock
	private PensionService pensionService;

	@Test
	public void retrieveInvestemntEndpointTriggered() {
		Investments investments = new Investments(new ArrayList<Investment>(Arrays.asList(new Investment("LG", 3987.0),
				new Investment("Honda", 7898.0), new Investment("Lenovo", 4522.0))));
		when(investmentService.retrieveInvestments(2967)).thenReturn(investments);
		investmentService.retrieveInvestments(2967);
		verify(investmentService, times(1)).retrieveInvestments(2967);
		verifyNoMoreInteractions(investmentService);
	}
}
