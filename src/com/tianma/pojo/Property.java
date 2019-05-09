package com.tianma.pojo;

import java.io.Serializable;

public class Property implements Serializable {
	
	//Property id
	private int id;
	
	//Property name
	private String name;
	
	//所属分类
	private Category category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
