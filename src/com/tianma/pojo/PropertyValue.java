package com.tianma.pojo;

import java.io.Serializable;

public class PropertyValue implements Serializable {

	//propertyValue id
	private int id;
	
	//value
	private String value;
	
	//value 所对应产品
	private Product product;
	
	//value 所对应属性
	private Property property;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	
	
}
