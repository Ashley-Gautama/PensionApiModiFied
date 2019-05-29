package com.example.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Investment;
import com.example.model.Investments;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

@Service
public class InvestmentService {
	@Autowired
	private RestTemplate template;
	private static final Logger logger = Logger.getLogger(InvestmentService.class.getName());

	public InvestmentService() {
		super();
	}

	public Investments retrieveInvestments(int accountId) {
		ResponseEntity respFake = responseFromMockAPI(accountId);
		ObjectMapper mapper = new ObjectMapper();
		Investment[] i = null;
		try {
			i = mapper.readValue(respFake.getBody().toString(), Investment[].class);
		} catch (IOException e) {
			logger.info("not an investment value is " + respFake.getBody());
		}
		return respFake.getStatusCode() == HttpStatus.OK ? (new Investments(Arrays.asList(i))) : null;
	}

	private ResponseEntity responseFromMockAPI(int accountId) {
		Investments investments = CreateInvestmentsForAccountId(accountId);
		WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
		wireMockServer.start();
		ResponseEntity respFake = null;
		template=new RestTemplate();
		if (investments.getInvestments().get(0) != null) {
			String jsonString = createJsonStringFromInvestments(investments);
			wireMockServer.stubFor(get(urlEqualTo("/mockAPI/getInvestmentsByAccountId/" + accountId)).willReturn(
					aResponse().withBody(jsonString).withStatus(200).withHeader("Content-Type", "application/json")));
			respFake = template.getForEntity(
					"http://localhost:" + wireMockServer.port() + "/mockAPI/getInvestmentsByAccountId/" + accountId,
					String.class);
		} else {
			wireMockServer.stubFor(get(urlEqualTo("/mockAPI/getInvestmentsByAccountId/" + accountId))
					.willReturn(aResponse().withBody("accountid does not exist")
							.withStatus(HttpStatus.FOUND.value()).withHeader("Content-Type", "text/plain")));
			respFake = template.getForEntity(
					"http://localhost:" + wireMockServer.port() + "/mockAPI/getInvestmentsByAccountId/" + accountId,
					String.class);
		}
		wireMockServer.stop();
		return respFake;
	}

	private Investments CreateInvestmentsForAccountId(int accountId) {
		List<Investment> listOfInvestments = new ArrayList<>();
		Investment firstInvestment = null;
		Investment secondInvestment = null;
		Investment thirdInvestment = null;
		if (accountId == 94356) {
			firstInvestment = createInvestment("Shell BV", 1330.0);
			secondInvestment = createInvestment("Microsoft", 2150.0);
			thirdInvestment = createInvestment("Apple", 6351.0);
		} else if (accountId == 2967) {
			firstInvestment = createInvestment("LG", 3987.0);
			secondInvestment = createInvestment("Honda", 7898.0);
			thirdInvestment = createInvestment("Lenovo", 4522.0);
		}
		listOfInvestments.addAll(Arrays.asList(firstInvestment, secondInvestment, thirdInvestment));
		return new Investments(listOfInvestments);
	}

	private String createJsonStringFromInvestments(Investments investments) {
		String temp = "";
		String json = "[";
		int size = investments.getInvestments().size();
		for (int i = 0; i < size; i++) {
			temp += "{\"name\":\"" + investments.getInvestments().get(i).getName() + "\",\"value\":\""
					+ investments.getInvestments().get(i).getValue() + "\"}";
			if (i < size - 1) {
				temp += ",";
			}
		}
		return json += temp + "]";
	}

	private Investment createInvestment(String company, double investmentValue) {
		return new Investment(company, investmentValue);
	}
}
