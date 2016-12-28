package com.example.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class ServiceAuthorization {

	public void setPassportEndpointUrl(String passportEndpointUrl) {
	}
	
	public Boolean authorizeRequest(HttpServletRequest httpServletRequest) {
		RestTemplate restTemplate = new RestTemplate();
		new HashMap<String, String>();
	     
//		restTemplate.exchange("http://localhost:8083/user", HttpMethod.GET, requestEntity, responseType)
		
		String result = restTemplate.getForObject("http://localhost:8083/user",String.class);
		System.out.println("**************************************************************");
		System.out.println("****************User Authorization**********************************************");
		System.out.println("Results " + result);

		return null;
	}
}
