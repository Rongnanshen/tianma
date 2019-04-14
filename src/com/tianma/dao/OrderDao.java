package com.tianma.dao;

import java.util.List;

import com.tianma.pojo.Order;

public interface OrderDao {

	//这些public static final 修饰的常量字符串，用于表示订单类型，在实体类Order的getStatusDesc方法中就用到了这些属性
	String waitPay = "waitPay";//待付款
	String waitDelivery = "waitDelivery";//待发货
	String waitConfirm = "waitConfirm";//待收货
	String waitReview = "waitReview";//待评价
	String finish = "finish";//完成
	String delete = "delete";//刪除

	/*
	 * 获取订单总数
	 */
	int getTotal();

	/*
	 * 增加订单
	 */
	void add(Order order);

	/*
	 * 修改订单
	 */
	void update(Order order);

	/*
	 * 删除订单
	 */
	void delete(int id);

	/*
	 * 通过id查询订单
	 */
	Order selectById(int id);

	/*
	 * 查询所有订单
	 */
	List<Order> selectAll();

	/*
	 * 分页查询所有订单
	 */
	List<Order> selectAll(int start, int count);

	/*
	 * 查询指定用户的订单
	 */
	List<Order> selectByUser(int uid, String excludedStatus);

	/*
	 * 分页查询指定用户的订单(排除某种订单状态，通常是"delete")
	 */
	List<Order> selectByUser(int uid, String excludedStatus, int start, int count);

}