package com.tianma.pojo;

import java.io.Serializable;

public class OrderItem implements Serializable {

	//订单项id
	private int id;
	
	//该订单项的商品数量
	private int number;
	
	//订单项产品
    private Product product;
    
    //订单项所属订单
    private Order order;
    
    //下单者
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
    
}
