package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Service
public class MasterDataDelegateImpl implements MasterDataDelegate{

	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	@CircuitBreaker(name="STATUS-FROM-MASTER-DATA-SERVICE",fallbackMethod="fallback")
	public String getStatusById(String statusId)
	{
		ResponseEntity<String> entityStatusText=this.restTemplate.getForEntity("http://API-GATEWAY/olx/advertise/status/", String.class);
		return entityStatusText.getBody();
		
	}
	public String fallback(int statusId,Exception ex)
	{
		System.out.println("Fallback method called:"+ex);
		return null;
	}
	
}
