package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class CustomWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {
    private static Logger log = LoggerFactory.getLogger(CustomWebSecurityExpressionHandler.class);

	@Autowired
	private ServiceAuthorization serviceAuthorization;
	
	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
	
//	public CustomWebSecurityExpressionHandler(Authentication a, FilterInvocation fi) {
//		super(a, fi);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        log.info("createSecurityExpressionRoot - ");
		
		CustomWebSecurityExpressionRoot customWebSecurityExpressionRoot = new CustomWebSecurityExpressionRoot(a, fi);
		customWebSecurityExpressionRoot.setServiceAuthorization(serviceAuthorization);
		customWebSecurityExpressionRoot.setTrustResolver(authenticationTrustResolver);
		return customWebSecurityExpressionRoot;
	}
	
	@Override
	public void setTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
		this.authenticationTrustResolver = authenticationTrustResolver;
		super.setTrustResolver(authenticationTrustResolver);
	}
}
