package com.example.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

	private FilterInvocation filterInvocation;
	private ServiceAuthorization serviceAuthorization;
	
	public void setServiceAuthorization(ServiceAuthorization serviceAuthorization) {
		this.serviceAuthorization = serviceAuthorization;
	}

	
	public CustomWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
		super(a, fi);
		this.filterInvocation = fi;
		// TODO Auto-generated constructor stub
	}

	public Boolean isAuthorized() {
		Boolean result = false;		
		return result;
	}
	
	
}
