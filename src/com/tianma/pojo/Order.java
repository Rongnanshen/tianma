package com.tianma.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tianma.dao.OrderDao;

public class Order implements Serializable {

	//订单id
    private int id;
    
    //订单号
	private String orderCode;
	
	//收货地址
    private String address;
    
    //邮编
    private String post;
    
    //收货人
    private String receiver;
    
    //收货人手机号
    private String mobile;
    
    //用户信息
    private String userMessage;
    
    //订单生成日期
    private Date createDate;
    
    //付款日期
    private Date payDate;
    
    //发货日期
    private Date deliveryDate;
    
    //确认收货日期
    private Date confirmDate;
    
    //下单者
    private User user;
    
    //订单里的订单项集合
    private List<OrderItem> orderItems;
    
    //订单总金额
    private float total;
    
    //商品总数量
    private int totalNumber;
    
    //订单状态
    private String status;

    public String getStatusDesc(){
        String desc ="未知";
        switch(status){
          case OrderDao.waitPay:
              desc="待付款";
              break;
          case OrderDao.waitDelivery:
              desc="待发货";
              break;
          case OrderDao.waitConfirm:
              desc="待收货";
              break;
          case OrderDao.waitReview:
              desc="待评价";
              break;
          case OrderDao.finish:
              desc="完成";
              break;
          case OrderDao.delete:
              desc="刪除";
              break;
          default:
              desc="未知";
        }
        return desc;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
