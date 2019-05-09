package com.tianma.pojo;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
	
	//review id
	private int id;
	
	//评论内容
	private String content;
	
	//评论日期
    private Date createDate;
    
    //评论者
    private User user;
    
    //所评论的产品
    private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    

}
