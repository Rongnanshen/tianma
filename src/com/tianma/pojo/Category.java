package com.tianma.pojo;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

	//id
	private int id;
	
	//product name
	private String name;
	
	//一级分类
    private List<Product> products;
    
    //一个分类又对应多个 List<Product>
    private List<List<Product>> productsByRow;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<List<Product>> getProductsByRow() {
		return productsByRow;
	}

	public void setProductsByRow(List<List<Product>> productsByRow) {
		this.productsByRow = productsByRow;
	}
    
    
}
