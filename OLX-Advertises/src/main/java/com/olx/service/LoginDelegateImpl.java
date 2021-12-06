package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class LoginDelegateImpl implements LoginDelegate{
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@CircuitBreaker(name="TOKEN-VALIDATION-SERVICE",fallbackMethod="fallbackValidToken")
	public boolean isValidToken(String authToken) {
		HttpHeaders headers=new HttpHeaders();
		headers.set("Authorization",authToken);
		HttpEntity entity=new HttpEntity(headers);
		ResponseEntity<Boolean> result=this.restTemplate.exchange("http://API-GATEWAY/olx/user/validate/token",
				HttpMethod.GET,entity, Boolean.class);
		return result.getBody();
	}
	public boolean fallbackValidToken(String authToken,Exception ex)
	{
		System.out.println("fallbackValidToken method called"+ ex);
		return false;
	}

}
