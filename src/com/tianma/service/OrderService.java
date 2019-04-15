package com.tianma.service;

import java.util.List;

import com.tianma.pojo.Order;

public interface OrderService {

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