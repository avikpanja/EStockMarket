package com.estock.company.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.estock.company.service.KafkaConsumer;
import com.estock.company.util.ResponseHandler;

public class RequestFilter extends GenericFilterBean {

	private static final String TOKEN_VALIDATOR_PATH = "/auth/v1/user/validateToken";
	private static final String AUITHORIZATION_KEY = "Authorization";
	
	//@Autowired 
	private DiscoveryClient discoveryClient;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		if(this.discoveryClient==null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            this.discoveryClient = webApplicationContext.getBean(DiscoveryClient.class);
		}
		RestTemplate restTemplate = new RestTemplate();	
		
		List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
		
		ServiceInstance serviceInstance = instances.get(0);		
		
		String baseUrl = serviceInstance.getUri().toString();
		
		baseUrl += TOKEN_VALIDATOR_PATH;
		
		//String baseUrl = TOKEN_VALIDATOR_PATH;
		
		
		//ResponseEntity<String> responseEntity = 
		try {
			restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(req), String.class);
			
		} 
		catch(Exception e) {
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		chain.doFilter(request, response);
	}

	private HttpEntity<?> getHeaders(HttpServletRequest req) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUITHORIZATION_KEY, req.getHeader(AUITHORIZATION_KEY));
		return new HttpEntity<>(headers);
	}

}
