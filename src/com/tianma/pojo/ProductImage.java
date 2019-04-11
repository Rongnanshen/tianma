package com.tianma.pojo;

public class ProductImage {
	
	// productImage id
	private int id;
	
	// 产品图片类型（productSingleImages 和 productDetailImages）
	private String type;
	
	// 图片所属产品
    private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    

}
