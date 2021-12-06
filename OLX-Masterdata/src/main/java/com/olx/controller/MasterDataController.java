package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterDataService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping(value="/olx/masterdata")
public class MasterDataController {

	@Autowired
	private MasterDataService masterDataService;
	
	@GetMapping("/advertise/category")
	@ApiOperation(value="Provide Category", notes="This service provides All category of products")
	public ResponseEntity<List<Category>> category(){
		return masterDataService.category();
	}
	
	@GetMapping(value = "/advertise/status")
	@ApiOperation(value = "Get Status of Ads", notes = "Returns all possible advertise status")
	public ResponseEntity<List<Status>> getStatus() {
		return masterDataService.getStatus();
	}
	 
	
}