package com.tianma.service;

import java.util.List;

import com.tianma.pojo.Order;
import com.tianma.pojo.OrderItem;

public interface OrderItemService {

	/*
	 * 获取订单项总数
	 */
	int getTotal();

	/*
	 * 增加订单项
	 */
	void add(OrderItem orderItem);

	/*
	 * 修改订单项
	 */
	void update(OrderItem orderItem);

	/*
	 * 删除订单项
	 */
	void delete(int id);

	/*
	 * 通过id查询订单项
	 */
	OrderItem selectById(int id);

	/*
	 * 查询某个用户的未生成订单的订单项(既购物车中的订单项)
	 */
	List<OrderItem> selectByUser(int uid);

	/*
	 * 分页查询某个用户的未生成订单的订单项(既购物车中的订单项)
	 */
	List<OrderItem> selectByUser(int uid, int start, int count);

	/*
	 * 查询某种订单下所有的订单项
	 */
	List<OrderItem> selectByOrder(int oid);

	/*
	 * 分页查询某种订单下所有的订单项
	 */
	List<OrderItem> selectByOrder(int oid, int start, int count);

	/*
	 * 为订单集合设置订单项集合
	 */
	void fill(List<Order> orders);

	/*
	 * 为单个订单设置订单项集合
	 */
	void fill(Order order);

	/*
	 * 通过产品查询订单项
	 */
	List<OrderItem> selectByProduct(int pid);

	/*
	 * 分页通过产品查询订单项
	 */
	List<OrderItem> selectByProduct(int pid, int start, int count);

	/*
	 * 获取某一种产品的销量。 
	 * 产品销量就是这种产品对应的订单项OrderItem的number字段的总和
	 */
	int getSaleCount(int pid);

}