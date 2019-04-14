package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianma.dao.OrderItemDao;
import com.tianma.pojo.Order;
import com.tianma.pojo.OrderItem;
import com.tianma.pojo.Product;
import com.tianma.pojo.User;
import com.tianma.util.DBUtil;

public class OrderItemDaoImpl implements OrderItemDao {

	private OrderItem orderItem = null;

	/*
	 * 获取订单项总数
	 */
	/**   
	 * <p>Title: getTotal</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#getTotal()   
	 */
	@Override
	public int getTotal() {

		int total = 0;
		String sql = "select count(*) from orderItem";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	/*
	 * 增加订单项
	 */
	/**   
	 * <p>Title: add</p>   
	 * <p>Description: </p>   
	 * @param orderItem   
	 * @see com.tianma.dao.OrderItemDao#add(com.tianma.pojo.OrderItem)   
	 */
	@Override
	public void add(OrderItem orderItem) {

		String sql = "insert into orderItem(id,pid,oid,uid,number) values(?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, orderItem.getId());
			ps.setInt(2, orderItem.getProduct().getId());

			// 订单项在创建的时候，是没有订单信息的
			if (null == orderItem.getOrder())
				ps.setInt(3, -1);
			else
				ps.setInt(3, orderItem.getOrder().getId());

			ps.setInt(4, orderItem.getUser().getId());
			ps.setInt(5, orderItem.getNumber());
			ps.execute();

			/*
			 * ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) { int id = rs.getInt(1);
			 * orderItem.setId(id); }
			 */
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 修改订单项
	 */
	/**   
	 * <p>Title: update</p>   
	 * <p>Description: </p>   
	 * @param orderItem   
	 * @see com.tianma.dao.OrderItemDao#update(com.tianma.pojo.OrderItem)   
	 */
	@Override
	public void update(OrderItem orderItem) {

		String sql = "update orderItem set pid= ?, oid=?, uid=?,number=?  where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, orderItem.getProduct().getId());
			if (null == orderItem.getOrder())
				ps.setInt(2, -1);
			else
				ps.setInt(2, orderItem.getOrder().getId());
			ps.setInt(3, orderItem.getUser().getId());
			ps.setInt(4, orderItem.getNumber());

			ps.setInt(5, orderItem.getId());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除订单项
	 */
	/**   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param id   
	 * @see com.tianma.dao.OrderItemDao#delete(int)   
	 */
	@Override
	public void delete(int id) {

		String sql = "delete from orderItem where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 通过id查询订单项
	 */
	/**   
	 * <p>Title: selectById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectById(int)   
	 */
	@Override
	public OrderItem selectById(int id) {

		orderItem = new OrderItem();
		String sql = "select * from orderItem where id = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int pid = rs.getInt("pid");
				int oid = rs.getInt("oid");
				int uid = rs.getInt("uid");
				int number = rs.getInt("number");
				Product product = new ProductDaoImpl().selectById(pid);
				User user = new UserDaoImpl().selectById(uid);
				orderItem.setProduct(product);
				orderItem.setUser(user);
				orderItem.setNumber(number);

				if (-1 != oid) {
					Order order = new OrderDaoImpl().selectById(oid);
					orderItem.setOrder(order);
				}

				orderItem.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return orderItem;
	}

	/*
	 * 查询某个用户的未生成订单的订单项(既购物车中的订单项)
	 */
	/**   
	 * <p>Title: selectByUser</p>   
	 * <p>Description: </p>   
	 * @param uid
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectByUser(int)   
	 */
	@Override
	public List<OrderItem> selectByUser(int uid) {
		return selectByUser(uid, 0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询某个用户的未生成订单的订单项(既购物车中的订单项)
	 */
	/**   
	 * <p>Title: selectByUser</p>   
	 * <p>Description: </p>   
	 * @param uid
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectByUser(int, int, int)   
	 */
	@Override
	public List<OrderItem> selectByUser(int uid, int start, int count) {

		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		String sql = "select * from orderItem where uid = ? and oid=-1 order by id desc limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, uid);
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				orderItem = new OrderItem();
				int id = rs.getInt(1);

				int pid = rs.getInt("pid");
				int oid = rs.getInt("oid");
				int number = rs.getInt("number");

				Product product = new ProductDaoImpl().selectById(pid);
				if (-1 != oid) {
					Order order = new OrderDaoImpl().selectById(oid);
					orderItem.setOrder(order);
				}

				User user = new UserDaoImpl().selectById(uid);
				orderItem.setProduct(product);

				orderItem.setUser(user);
				orderItem.setNumber(number);
				orderItem.setId(id);
				orderItems.add(orderItem);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return orderItems;
	}

	/*
	 * 查询某种订单下所有的订单项
	 */
	/**   
	 * <p>Title: selectByOrder</p>   
	 * <p>Description: </p>   
	 * @param oid
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectByOrder(int)   
	 */
	@Override
	public List<OrderItem> selectByOrder(int oid) {
		return selectByOrder(oid, 0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询某种订单下所有的订单项
	 */
	/**   
	 * <p>Title: selectByOrder</p>   
	 * <p>Description: </p>   
	 * @param oid
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectByOrder(int, int, int)   
	 */
	@Override
	public List<OrderItem> selectByOrder(int oid, int start, int count) {

		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		String sql = "select * from orderItem where oid = ? limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, oid);
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orderItem = new OrderItem();
				int id = rs.getInt(1);

				int pid = rs.getInt("pid");
				int uid = rs.getInt("uid");
				int number = rs.getInt("number");

				Product product = new ProductDaoImpl().selectById(pid);
				if (-1 != oid) {
					Order order = new OrderDaoImpl().selectById(oid);
					orderItem.setOrder(order);
				}

				User user = new UserDaoImpl().selectById(uid);
				orderItem.setProduct(product);

				orderItem.setUser(user);
				orderItem.setNumber(number);
				orderItem.setId(id);
				orderItems.add(orderItem);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return orderItems;
	}

	/*
	 * 为订单集合设置订单项集合
	 */
	/**   
	 * <p>Title: fill</p>   
	 * <p>Description: </p>   
	 * @param orders   
	 * @see com.tianma.dao.OrderItemDao#fill(java.util.List)   
	 */
	@Override
	public void fill(List<Order> orders) {

		for (Order order : orders) {

			// 获取该订单下的所有订单项
			List<OrderItem> orderItems = selectByOrder(order.getId());

			float total = 0;
			int totalNumber = 0;

			for (OrderItem orderItem : orderItems) {

				// 获取该订单的总计金额
				total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();

				// 获取该订单下的所有商品数量总和
				totalNumber += orderItem.getNumber();
			}
			order.setTotal(total);
			order.setOrderItems(orderItems);
			order.setTotalNumber(totalNumber);
		}

	}

	/*
	 * 为单个订单设置订单项集合
	 */
	/**   
	 * <p>Title: fill</p>   
	 * <p>Description: </p>   
	 * @param order   
	 * @see com.tianma.dao.OrderItemDao#fill(com.tianma.pojo.Order)   
	 */
	@Override
	public void fill(Order order) {

		List<OrderItem> orderItems = selectByOrder(order.getId());

		float total = 0;

		for (OrderItem orderItem : orderItems) {
			// 获取订单总金额
			total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
		}

		order.setTotal(total);
		order.setOrderItems(orderItems);
	}

	/*
	 * 通过产品查询订单项
	 */
	/**   
	 * <p>Title: selectByProduct</p>   
	 * <p>Description: </p>   
	 * @param pid
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectByProduct(int)   
	 */
	@Override
	public List<OrderItem> selectByProduct(int pid) {
		return selectByProduct(pid, 0, Short.MAX_VALUE);
	}

	/*
	 * 分页通过产品查询订单项
	 */
	/**   
	 * <p>Title: selectByProduct</p>   
	 * <p>Description: </p>   
	 * @param pid
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#selectByProduct(int, int, int)   
	 */
	@Override
	public List<OrderItem> selectByProduct(int pid, int start, int count) {

		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		String sql = "select * from orderItem where pid = ? limit ?,?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, pid);
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				orderItem = new OrderItem();
				int id = rs.getInt(1);

				int uid = rs.getInt("uid");
				int oid = rs.getInt("oid");
				int number = rs.getInt("number");

				Product product = new ProductDaoImpl().selectById(pid);
				if (-1 != oid) {
					Order order = new OrderDaoImpl().selectById(oid);
					orderItem.setOrder(order);
				}

				User user = new UserDaoImpl().selectById(uid);
				orderItem.setProduct(product);

				orderItem.setUser(user);
				orderItem.setNumber(number);
				orderItem.setId(id);
				orderItems.add(orderItem);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return orderItems;
	}

	/*
	 * 获取某一种产品的销量。 
	 * 产品销量就是这种产品对应的订单项OrderItem的number字段的总和
	 */
	/**   
	 * <p>Title: getSaleCount</p>   
	 * <p>Description: </p>   
	 * @param pid
	 * @return   
	 * @see com.tianma.dao.OrderItemDao#getSaleCount(int)   
	 */
	@Override
	public int getSaleCount(int pid) {
		
		int total = 0;
		String sql = "select sum(number) from orderItem where pid = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

}
