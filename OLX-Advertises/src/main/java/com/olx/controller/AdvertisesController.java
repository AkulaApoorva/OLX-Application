package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.andrewoma.dexx.collection.ArrayList;
import com.olx.dto.Advertise;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/advertise")
public class AdvertisesController {
	@Autowired
	AdvertiseService advertiseService;
	static ArrayList<Advertise> advertises = new ArrayList<Advertise>();
	
	@PostMapping(value="/",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="creates new Advertise")
	public ResponseEntity<Advertise> CreateNewAdvertise(@RequestHeader("auth-token") String authToken,@RequestBody Advertise advertise) {
		ResponseEntity<Advertise> responseAdvertise = advertiseService.createNewAdvertise(advertise, authToken);
		return new ResponseEntity<Advertise>(HttpStatus.CREATED);
	}
	

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="updates Advertise")
	public ResponseEntity<Advertise> updateAdvertise(@RequestBody Advertise advertise, @RequestHeader("auth-token") String authToken,
			@PathVariable("id") String advertiseId) {
		ResponseEntity<Advertise> responseAdvertise = advertiseService.updateAdvertise(advertise, authToken , advertiseId);
		return new ResponseEntity<Advertise>(HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="gets all Advertises")
	public ResponseEntity<List<Advertise>> getAllAdvertises(@RequestHeader("auth-token") String authToken) {
		List<Advertise> listOfAdvertise = advertiseService.getAllAdvertises(authToken);
		return new ResponseEntity<List<Advertise>>(listOfAdvertise, HttpStatus.OK);
	}

	@GetMapping(value = "/user/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="gets Advertise by user Id")
	public ResponseEntity<Advertise> getAdvertiseByUserPostId(@RequestHeader("auth-token") String authToken,
			@PathVariable("postId") String postId) {
		ResponseEntity<Advertise> advertise = advertiseService.getAdvertiseByUserPostId(authToken, postId);
		return new ResponseEntity<Advertise>(HttpStatus.OK);

	}

	@DeleteMapping(value = "/{postId}")
	@ApiOperation(value="deletes Advertise by Id")
	public ResponseEntity<Boolean> deleteAdvertiseByPostId(@RequestHeader("auth-token") String authToken,
			@PathVariable("postId") String postId) {
		ResponseEntity<Boolean> isDeleted = advertiseService.deleteAdvertiseByPostId(authToken, postId);
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}

	
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="gets list of Advertises according to the search text")
	public ResponseEntity<Advertise> getAdvertiseBySearchText(@RequestParam("searchText") String searchText) {
		return advertiseService.getAdvertiseBySearchText(searchText);
	}
	
	@GetMapping(value = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="gets Advertise by Id")
	public ResponseEntity<Advertise> getAdvertiseByPostId(@RequestHeader("auth-token") String authToken,
			@PathVariable("postId") String postId) {
		ResponseEntity<Advertise> advertise = advertiseService.getAdvertiseByPostId(authToken, postId);
		return new ResponseEntity<Advertise>(HttpStatus.OK);
	}
	
	
}
