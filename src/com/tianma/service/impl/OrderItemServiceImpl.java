package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.OrderItemDao;
import com.tianma.dao.impl.OrderItemDaoImpl;
import com.tianma.pojo.Order;
import com.tianma.pojo.OrderItem;
import com.tianma.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemDao orderItemDao = null;
	
	/*
	 * 获取订单项总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#getTotal()
	 */
	@Override
	public int getTotal() {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.getTotal();
	}

	/*
	 * 增加订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#add(com.tianma.pojo.OrderItem)
	 */
	@Override
	public void add(OrderItem orderItem) {
		orderItemDao = new OrderItemDaoImpl();
		orderItemDao.add(orderItem);
	}

	/*
	 * 修改订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#update(com.tianma.pojo.OrderItem)
	 */
	@Override
	public void update(OrderItem orderItem) {
		orderItemDao = new OrderItemDaoImpl();
		orderItemDao.update(orderItem);
	}

	/*
	 * 删除订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#delete(int)
	 */
	@Override
	public void delete(int id) {
		orderItemDao = new OrderItemDaoImpl();
		orderItemDao.delete(id);
	}

	/*
	 * 通过id查询订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectById(int)
	 */
	@Override
	public OrderItem selectById(int id) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectById(id);
	}

	/*
	 * 查询某个用户的未生成订单的订单项(既购物车中的订单项)
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectByUser(int)
	 */
	@Override
	public List<OrderItem> selectByUser(int uid) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectByUser(uid);
	}

	/*
	 * 分页查询某个用户的未生成订单的订单项(既购物车中的订单项)
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectByUser(int, int, int)
	 */
	@Override
	public List<OrderItem> selectByUser(int uid, int start, int count) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectByUser(uid, start, count);
	}

	/*
	 * 查询某种订单下所有的订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectByOrder(int)
	 */
	@Override
	public List<OrderItem> selectByOrder(int oid) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectByOrder(oid);
	}

	/*
	 * 分页查询某种订单下所有的订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectByOrder(int, int, int)
	 */
	@Override
	public List<OrderItem> selectByOrder(int oid, int start, int count) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectByOrder(oid, start, count);
	}

	/*
	 * 为订单集合设置订单项集合
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#fill(java.util.List)
	 */
	@Override
	public void fill(List<Order> orders) {
		orderItemDao = new OrderItemDaoImpl();
		orderItemDao.fill(orders);
	}

	/*
	 * 为单个订单设置订单项集合
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#fill(com.tianma.pojo.Order)
	 */
	@Override
	public void fill(Order order) {
		orderItemDao = new OrderItemDaoImpl();
		orderItemDao.fill(order);
	}

	/*
	 * 通过产品查询订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectByProduct(int)
	 */
	@Override
	public List<OrderItem> selectByProduct(int pid) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectByProduct(pid);
	}

	/*
	 * 分页通过产品查询订单项
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#selectByProduct(int, int, int)
	 */
	@Override
	public List<OrderItem> selectByProduct(int pid, int start, int count) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.selectByProduct(pid, start, count);
	}

	/*
	 * 获取某一种产品的销量。 
	 * 产品销量就是这种产品对应的订单项OrderItem的number字段的总和
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.OrderItemService#getSaleCount(int)
	 */
	@Override
	public int getSaleCount(int pid) {
		orderItemDao = new OrderItemDaoImpl();
		return orderItemDao.getSaleCount(pid);
	}

	
}
