package com.olx.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.dto.Status;

@Service
public interface MasterDataService {

	ResponseEntity<List<Category>> category();

	ResponseEntity<List<Status>> getStatus();
}
