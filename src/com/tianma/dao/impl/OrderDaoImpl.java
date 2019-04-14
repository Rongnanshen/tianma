package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianma.dao.OrderDao;
import com.tianma.pojo.Order;
import com.tianma.pojo.User;
import com.tianma.util.DBUtil;
import com.tianma.util.DateUtil;

public class OrderDaoImpl implements OrderDao {

	private Order order = null;
	/*
	 * 获取订单总数
	 */
    /**   
	 * <p>Title: getTotal</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.dao.OrderDao#getTotal()   
	 */
    @Override
	public int getTotal() {
        
    	int total = 0;
        String sql = "select count(*) from order_";
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
     * 增加订单
     */
    /**   
	 * <p>Title: add</p>   
	 * <p>Description: </p>   
	 * @param order   
	 * @see com.tianma.dao.OrderDao#add(com.tianma.pojo.Order)   
	 */
    @Override
	public void add(Order order) {

        String sql = "insert into order_(id,orderCode,address,post,receiver,mobile,userMessage,createDate,payDate,deliveryDate,confirmDate,uid,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
        	ps.setInt(1, order.getId());
            ps.setString(2, order.getOrderCode());
            ps.setString(3, order.getAddress());
            ps.setString(4, order.getPost());
            ps.setString(5, order.getReceiver());
            ps.setString(6, order.getMobile());
            ps.setString(7, order.getUserMessage());
            
            ps.setTimestamp(8,  DateUtil.d2t(order.getCreateDate()));
            ps.setTimestamp(9,  DateUtil.d2t(order.getPayDate()));
            ps.setTimestamp(10,  DateUtil.d2t(order.getDeliveryDate()));
            ps.setTimestamp(11,  DateUtil.d2t(order.getConfirmDate()));
            ps.setInt(12, order.getUser().getId());
            ps.setString(13, order.getStatus());

            ps.execute();
 
            /*ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                order.setId(id);
            }*/
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    /*
     * 修改订单
     */
    /**   
	 * <p>Title: update</p>   
	 * <p>Description: </p>   
	 * @param order   
	 * @see com.tianma.dao.OrderDao#update(com.tianma.pojo.Order)   
	 */
    @Override
	public void update(Order order) {

        String sql = "update order_ set address= ?, post=?, receiver=?,mobile=?,userMessage=? ,createDate = ? , payDate =? , deliveryDate =?, confirmDate = ? , orderCode =?, uid=?, status=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, order.getAddress());
            ps.setString(2, order.getPost());
            ps.setString(3, order.getReceiver());
            ps.setString(4, order.getMobile());
            ps.setString(5, order.getUserMessage());
            ps.setTimestamp(6, DateUtil.d2t(order.getCreateDate()));;
            ps.setTimestamp(7, DateUtil.d2t(order.getPayDate()));;
            ps.setTimestamp(8, DateUtil.d2t(order.getDeliveryDate()));;
            ps.setTimestamp(9, DateUtil.d2t(order.getConfirmDate()));;
            ps.setString(10, order.getOrderCode());
            ps.setInt(11, order.getUser().getId());
            ps.setString(12, order.getStatus());
            ps.setInt(13, order.getId());
            ps.execute();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    /*
     * 删除订单
     */
    /**   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param id   
	 * @see com.tianma.dao.OrderDao#delete(int)   
	 */
    @Override
	public void delete(int id) {
 
    	String sql = "delete from order_ where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, id);
 
            ps.execute();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    /*
     * 通过id查询订单
     */
    /**   
	 * <p>Title: selectById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tianma.dao.OrderDao#selectById(int)   
	 */
    @Override
	public Order selectById(int id) {
    	
        order = new Order();
        String sql = "select * from order_ where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, id);
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
            	String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String post = rs.getString("post");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int uid =rs.getInt("uid");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                
                order.setOrderCode(orderCode);
                order.setAddress(address);
                order.setPost(post);
                order.setReceiver(receiver);
                order.setMobile(mobile);
                order.setUserMessage(userMessage);
                order.setCreateDate(createDate);
                order.setPayDate(payDate);
                order.setDeliveryDate(deliveryDate);
                order.setConfirmDate(confirmDate);
                User user = new UserDaoImpl().selectById(uid);
                order.setUser(user);
                order.setStatus(status);
                
                order.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return order;
    }
 
    /*
     * 查询所有订单
     */
    /**   
	 * <p>Title: selectAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.dao.OrderDao#selectAll()   
	 */
    @Override
	public List<Order> selectAll() {
        return selectAll(0, Short.MAX_VALUE);
    }
 
    /*
     * 分页查询所有订单
     */
    /**   
	 * <p>Title: selectAll</p>   
	 * <p>Description: </p>   
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.dao.OrderDao#selectAll(int, int)   
	 */
    @Override
	public List<Order> selectAll(int start, int count) {
        
    	List<Order> orders = new ArrayList<Order>();
 
        String sql = "select * from order_ limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                order = new Order();
            	String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String post = rs.getString("post");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
                Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
                Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
                Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
                int uid =rs.getInt("uid");                
                
                int id = rs.getInt("id");
                order.setId(id);
                order.setOrderCode(orderCode);
                order.setAddress(address);
                order.setPost(post);
                order.setReceiver(receiver);
                order.setMobile(mobile);
                order.setUserMessage(userMessage);
                order.setCreateDate(createDate);
                order.setPayDate(payDate);
                order.setDeliveryDate(deliveryDate);
                order.setConfirmDate(confirmDate);
                User user = new UserDaoImpl().selectById(uid);
                order.setUser(user);
                order.setStatus(status);
                orders.add(order);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return orders;
    }
    
    /*
     * 查询指定用户的订单
     */
    /**   
	 * <p>Title: selectByUser</p>   
	 * <p>Description: </p>   
	 * @param uid
	 * @param excludedStatus
	 * @return   
	 * @see com.tianma.dao.OrderDao#selectByUser(int, java.lang.String)   
	 */
    @Override
	public List<Order> selectByUser(int uid,String excludedStatus) {
        return selectByUser(uid,excludedStatus,0, Short.MAX_VALUE);
    }
    
    /*
     * 分页查询指定用户的订单(排除某种订单状态，通常是"delete")
     */
    /**   
	 * <p>Title: selectByUser</p>   
	 * <p>Description: </p>   
	 * @param uid
	 * @param excludedStatus
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.dao.OrderDao#selectByUser(int, java.lang.String, int, int)   
	 */
    @Override
	public List<Order> selectByUser(int uid, String excludedStatus, int start, int count) {
    	
    	List<Order> orders = new ArrayList<Order>();
    	
    	String sql = "select * from order_ where uid = ? and status != ? limit ?,? ";
    	
    	try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
    		
    		ps.setInt(1, uid);
    		ps.setString(2, excludedStatus);
    		ps.setInt(3, start);
    		ps.setInt(4, count);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while (rs.next()) {
    			order = new Order();
    			String orderCode =rs.getString("orderCode");
    			String address = rs.getString("address");
    			String post = rs.getString("post");
    			String receiver = rs.getString("receiver");
    			String mobile = rs.getString("mobile");
    			String userMessage = rs.getString("userMessage");
    			String status = rs.getString("status");
    			Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
    			Date payDate = DateUtil.t2d( rs.getTimestamp("payDate"));
    			Date deliveryDate = DateUtil.t2d( rs.getTimestamp("deliveryDate"));
    			Date confirmDate = DateUtil.t2d( rs.getTimestamp("confirmDate"));
               
    			int id = rs.getInt("id");
    			order.setId(id);
    			order.setOrderCode(orderCode);
    			order.setAddress(address);
    			order.setPost(post);
    			order.setReceiver(receiver);
    			order.setMobile(mobile);
    			order.setUserMessage(userMessage);
    			order.setCreateDate(createDate);
    			order.setPayDate(payDate);
    			order.setDeliveryDate(deliveryDate);
    			order.setConfirmDate(confirmDate);
    			User user = new UserDaoImpl().selectById(uid);
    			order.setStatus(status);
    			order.setUser(user);
    			orders.add(order);
    		}
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    	return orders;
    }
	
}
