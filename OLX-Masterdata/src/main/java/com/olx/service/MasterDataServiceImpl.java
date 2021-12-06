package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.exception.InvalidStatusIdException;
import com.olx.repository.CategoryRepository;
import com.olx.repository.StatusRepository;

@Service
public class MasterDataServiceImpl implements MasterDataService {

	
	@Autowired
	private StatusRepository statusRepository;
	
	
	@Autowired 
	private CategoryRepository categoryRepository;
	 
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public ResponseEntity<List<Category>> category() {
		List<CategoryEntity> masterdataList = categoryRepository.findAll();
		System.out.println(masterdataList);
		List<Category> categoryList = new ArrayList<>();
		if (masterdataList == null)
			return new ResponseEntity<List<Category>>(HttpStatus.NOT_FOUND);
		else {
			for(CategoryEntity masterdata:masterdataList) {
				categoryList.add(new Category(masterdata.getId(),masterdata.getCategory()));
			}
			System.out.println(categoryList);
			return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<Status>> getStatus() {
		List<StatusEntity> masterdataList = statusRepository.findAll();
		List<Status> statusList = new ArrayList<>();
		if (masterdataList == null)
			return new ResponseEntity<List<Status>>(HttpStatus.NOT_FOUND);
		else {
			for(StatusEntity masterdata:masterdataList) {
				statusList.add(new Status(masterdata.getId(),masterdata.getStatus()));
			}
			return new ResponseEntity<List<Status>>(statusList, HttpStatus.OK);
		}
	}

		
}