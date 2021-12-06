package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="categories")
public class CategoryEntity {

	@Id
	private int id;
	
	@Column(name="name")
	private String category;
	
	private String description;
	
	public CategoryEntity() {
		// TODO Auto-generated constructor stub
	}

	public CategoryEntity(int id, String category, String description) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", category=" + category + ", description=" + description + "]";
	}
	
}