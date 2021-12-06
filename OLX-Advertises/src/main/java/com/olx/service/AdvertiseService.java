package com.olx.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.olx.dto.Advertise;

public interface AdvertiseService {

	
	ResponseEntity<Advertise> createNewAdvertise(Advertise advertise, String authToken);

	ResponseEntity<Advertise> updateAdvertise(Advertise advertise, String authToken, String advertiseId);

	List<Advertise> getAllAdvertises(String authToken);

	ResponseEntity<Advertise> getAdvertiseByUserPostId(String authToken, String postId);

	ResponseEntity<Boolean> deleteAdvertiseByPostId(String authToken, String id);

	ResponseEntity<Advertise> getAdvertiseBySearchText(String searchText);

	ResponseEntity<Advertise> getAdvertiseByPostId(String authToken, String postId);

	



	

	

	



	

}
