package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.OrderDao;
import com.tianma.dao.impl.OrderDaoImpl;
import com.tianma.pojo.Order;
import com.tianma.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = null;
	
	/*
	 * 获取订单总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#getTotal()
	 */
	@Override
	public int getTotal() {
		orderDao = new OrderDaoImpl();
		return orderDao.getTotal();
	}

	/*
	 * 增加订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#add(com.tianma.pojo.Order)
	 */
	@Override
	public void add(Order order) {
		orderDao = new OrderDaoImpl();
		orderDao.add(order);
	}

	/*
	 * 修改订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#update(com.tianma.pojo.Order)
	 */
	@Override
	public void update(Order order) {
		orderDao = new OrderDaoImpl();
		orderDao.update(order);
	}

	/*
	 * 删除订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#delete(int)
	 */
	@Override
	public void delete(int id) {
		orderDao = new OrderDaoImpl();
		orderDao.delete(id);
	}

	/*
	 * 通过id查询订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#selectById(int)
	 */
	@Override
	public Order selectById(int id) {
		orderDao = new OrderDaoImpl();
		return orderDao.selectById(id);
	}

	/*
	 * 查询所有订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#selectAll()
	 */
	@Override
	public List<Order> selectAll() {
		orderDao = new OrderDaoImpl();
		return orderDao.selectAll();
	}

	/*
	 * 分页查询所有订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#selectAll(int, int)
	 */
	@Override
	public List<Order> selectAll(int start, int count) {
		orderDao = new OrderDaoImpl();
		return orderDao.selectAll(start, count);
	}

	/*
	 * 查询指定用户的订单
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#selectByUser(int, java.lang.String)
	 */
	@Override
	public List<Order> selectByUser(int uid, String excludedStatus) {
		orderDao = new OrderDaoImpl();
		return orderDao.selectByUser(uid, excludedStatus);
	}

	/*
	 * 分页查询指定用户的订单(排除某种订单状态，通常是"delete")
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderService#selectByUser(int, java.lang.String, int, int)
	 */
	@Override
	public List<Order> selectByUser(int uid, String excludedStatus, int start, int count) {
		orderDao = new OrderDaoImpl();
		return orderDao.selectByUser(uid, excludedStatus, start, count);
	}
	
}
