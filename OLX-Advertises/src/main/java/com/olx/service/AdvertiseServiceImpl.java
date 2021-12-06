package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.exception.InvalidUserException;
import com.olx.repository.AdvertiseRepository;

@Service
public abstract class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	AdvertiseRepository advertiseRepository;
	@Autowired
	LoginDelegate loginDelegate;
	@Autowired
	MasterDataDelegate masterDataDelegate;

	List<Advertise> advertiseList = new ArrayList<>();
	int advertiseId = 0;
	
	public ResponseEntity<Advertise> createNewAdvertise(Advertise advertise, String authToken) {
		/*
		 if(loginDelegate.isvalidToken(authToken)) { throw new
		 InvalidUserException(authToken); }
		 */
		advertiseRepository.save(advertise);
		// for OLX - Master Data Service
		String status = masterDataDelegate.getStatusById(advertise.getStatus());
		advertise.setStatus(status);
		return new ResponseEntity<Advertise>(advertise, HttpStatus.CREATED);
	}


	
	public ResponseEntity<Advertise> updateAdvertise(Advertise advertise, String authToken, String advertiseId) {
		if (loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			Optional<Advertise> ads = null;
			if (advertiseRepository.findById(advertiseId) != null) {
				ads = advertiseRepository.findById(advertiseId);
				String newId = ads.get().getId(); 
				advertiseRepository.deleteById(newId);
				advertise.setId(newId);
				advertiseRepository.save(advertise);
				return new ResponseEntity<Advertise>(advertise, HttpStatus.OK);
			} else
				return new ResponseEntity<Advertise>(advertise, HttpStatus.NOT_FOUND);
		}
	}

	
	public List<Advertise> getAllAdvertises(String authToken) {
		if (loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			List<Advertise> adsList = advertiseRepository.findAll();
			if (!adsList.isEmpty())
				return adsList;
			else
				return null;
		}
	}
	
	public ResponseEntity<Advertise> getAdvertiseByUserPostId(String authToken, String postId) {
		if (loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			Optional<Advertise> ad = null;
			if (advertiseRepository.findById(postId) != null) {
				ad = advertiseRepository.findById(postId);
				return new ResponseEntity<Advertise>(ad.get(), HttpStatus.FOUND);
			} else
				return new ResponseEntity<Advertise>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Boolean> deleteAdvertiseByPostId(String authToken, String postId) {
		if (loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			if (advertiseRepository.findById(postId) != null)
				advertiseRepository.deleteById(postId);
			else
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			List<Advertise> adList = new ArrayList<>();
			adList = advertiseRepository.findAll();
			if (!adList.contains(advertiseRepository.findById(postId)))
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			else
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}


	public ResponseEntity<Advertise> getAdvertiseByPostId(String authToken, String postId) {
		if (loginDelegate.isValidToken(authToken))
			throw new InvalidUserException(authToken);
		else {
			Optional<Advertise> ad = null;
			if (advertiseRepository.findById(postId) != null) {
				ad = advertiseRepository.findById(postId);
				return new ResponseEntity<Advertise>(ad.get(), HttpStatus.FOUND);
			} else
				return new ResponseEntity<Advertise>(HttpStatus.NOT_FOUND);
		}	
	}

	public ResponseEntity<Advertise> getAdvertiseBySearchText(String searchText) {
		List<Advertise> adList = advertiseRepository.findAll();
		for (int i = 0; i < adList.size(); i++) {
			if (searchText.equals(adList.get(i).getCategory()))
				return new ResponseEntity<Advertise>(adList.get(i), HttpStatus.FOUND);
		}
		return new ResponseEntity<Advertise>(adList.get(0), HttpStatus.NOT_FOUND);
	}
	

}