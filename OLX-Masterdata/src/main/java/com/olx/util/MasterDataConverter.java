package com.olx.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.olx.dto.Category;
import com.olx.entity.CategoryEntity;

@Component
public class MasterDataConverter {
	
	@Autowired
	ModelMapper modelMapper;
	
	
	public static Category  covertCategoryEntityIntoCategoryDTO(CategoryEntity categoryEntity) {
		Category categoryDto = new Category();
		/*
		 * categoryDto.setId(categoryEntity.getId());
		 * categoryDto.setCategory(categoryEntity.getCategory());
		 * categoryDto.setDescription(categoryEntity.getDesc());
		 */
		
		
		return categoryDto;
	}

}