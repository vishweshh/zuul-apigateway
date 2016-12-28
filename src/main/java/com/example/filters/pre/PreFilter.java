package com.example.filters.pre;

import java.text.MessageFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {
	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		System.out.println("********************************************REROUTING*************************");
		System.out.println("Headers " + request.getHeaderNames());
		System.out.println("User  " + request.getUserPrincipal().getName());
		System.out.println("URI " + request.getRequestURI());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		//String endpointURL = "http://<host>/api/Authorizaton/IsAuthorized?lanId={0}&applicationName={1}";
		String endpointURL = "http://localhost:9000/api/Authorizaton/IsAuthorized?lanId={0}&applicationName={1}";
		ResponseEntity<String> response = null;
		try {
			String formatted_URL = MessageFormat.format(endpointURL, request.getUserPrincipal().getName(),
					request.getRequestURI());
			response = restTemplate.exchange(formatted_URL, HttpMethod.GET, entity,
					String.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in Authorization");
		}

		System.out.println("Authorized ? - " + response.getBody());
		Boolean result = new Boolean(response.getBody()); 
		if (result) {
			log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
			log.info("Time - " + Calendar.getInstance().getTime());
			log.info("Authorized");
			return null;
		} else {
			ctx.setResponseStatusCode(403);
			ctx.setResponseBody("Access is Denied in Passport");
			ctx.setSendZuulResponse(false);
			throw new RuntimeException("Access is Denied in Passport");
		}
	}

}