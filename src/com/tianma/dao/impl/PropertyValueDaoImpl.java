package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianma.dao.PropertyValueDao;
import com.tianma.pojo.Product;
import com.tianma.pojo.Property;
import com.tianma.pojo.PropertyValue;
import com.tianma.util.DBUtil;

public class PropertyValueDaoImpl implements PropertyValueDao {

	private PropertyValue propertyValue = null;

	/*
	 * 获取属性值总数
	 */
	/**   
	 * <p>Title: getTotal</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.dao.PropertyValueDao#getTotal()   
	 */
	@Override
	public int getTotal() {

		int total = 0;
		String sql = "select count(*) from propertyValue";

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
	 * 增加属性值
	 */
	/**   
	 * <p>Title: add</p>   
	 * <p>Description: </p>   
	 * @param propertyValue   
	 * @see com.tianma.dao.PropertyValueDao#add(com.tianma.pojo.PropertyValue)   
	 */
	@Override
	public void add(PropertyValue propertyValue) {

		String sql = "insert into propertyValue(id,pid,ptid,value) values(?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, propertyValue.getId());
			ps.setInt(2, propertyValue.getProduct().getId());
			ps.setInt(3, propertyValue.getProperty().getId());
			ps.setString(4, propertyValue.getValue());
			ps.execute();

			/*
			 * ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) { int id = rs.getInt(1);
			 * propertyValue.setId(id); }
			 */
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 修改属性值
	 */
	/**   
	 * <p>Title: update</p>   
	 * <p>Description: </p>   
	 * @param propertyValue   
	 * @see com.tianma.dao.PropertyValueDao#update(com.tianma.pojo.PropertyValue)   
	 */
	@Override
	public void update(PropertyValue propertyValue) {

		String sql = "update propertyValue set pid= ?, ptid=?, value=?  where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, propertyValue.getProduct().getId());
			ps.setInt(2, propertyValue.getProperty().getId());
			ps.setString(3, propertyValue.getValue());
			ps.setInt(4, propertyValue.getId());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除属性值
	 */
	/**   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param id   
	 * @see com.tianma.dao.PropertyValueDao#delete(int)   
	 */
	@Override
	public void delete(int id) {

		String sql = "delete from propertyValue where id = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);
			ps.execute(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 通过id查询
	 */
	/**   
	 * <p>Title: selectById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tianma.dao.PropertyValueDao#selectById(int)   
	 */
	@Override
	public PropertyValue selectById(int id) {

		propertyValue = new PropertyValue();
		String sql = "select * from propertyValue where id = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int pid = rs.getInt("pid");
				int ptid = rs.getInt("ptid");
				String value = rs.getString("value");

				Product product = new ProductDaoImpl().selectById(pid);
				Property property = new PropertyDaoImpl().selectById(ptid);
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValue.setValue(value);
				propertyValue.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return propertyValue;
	}

	/*
	 * 通过属性id和产品id查询
	 */
	/**   
	 * <p>Title: selectByProductProperty</p>   
	 * <p>Description: </p>   
	 * @param ptid
	 * @param pid
	 * @return   
	 * @see com.tianma.dao.PropertyValueDao#selectByProductProperty(int, int)   
	 */
	@Override
	public PropertyValue selectByProductProperty(int ptid, int pid) {

		PropertyValue propertyValue = null;
		String sql = "select * from propertyValue where ptid = ? and pid = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, ptid);
			ps.setInt(2, pid);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				propertyValue = new PropertyValue();
				int id = rs.getInt("id");

				String value = rs.getString("value");

				Product product = new ProductDaoImpl().selectById(pid);
				Property property = new PropertyDaoImpl().selectById(ptid);
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValue.setValue(value);
				propertyValue.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return propertyValue;
	}

	/*
	 * 查询所有属性值
	 */
	/**   
	 * <p>Title: selectAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.dao.PropertyValueDao#selectAll()   
	 */
	@Override
	public List<PropertyValue> selectAll() {
		return selectAll(0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询所有属性值
	 */
	/**   
	 * <p>Title: selectAll</p>   
	 * <p>Description: </p>   
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.dao.PropertyValueDao#selectAll(int, int)   
	 */
	@Override
	public List<PropertyValue> selectAll(int start, int count) {
		List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

		String sql = "select * from propertyValue limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				propertyValue = new PropertyValue();
				int id = rs.getInt("id");

				int pid = rs.getInt("pid");
				int ptid = rs.getInt("ptid");
				String value = rs.getString("value");

				Product product = new ProductDaoImpl().selectById(pid);
				Property property = new PropertyDaoImpl().selectById(ptid);
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValue.setValue(value);
				propertyValue.setId(id);
				propertyValues.add(propertyValue);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return propertyValues;
	}

	/*
	 * 初始化某个产品的属性值： 
	 * 1. 根据分类获取所有的属性 
	 * 2. 遍历每一个属性 
	 * 2.1 根据属性和产品，获取属性值 
	 * 2.2如果属性值不存在，就创建一个属性值对象
	 */
	/**   
	 * <p>Title: init</p>   
	 * <p>Description: </p>   
	 * @param product   
	 * @see com.tianma.dao.PropertyValueDao#init(com.tianma.pojo.Product)   
	 */
	@Override
	public void init(Product product) {
		//1. 根据分类获取所有的属性 
		List<Property> properties = new PropertyDaoImpl().selectAll(product.getCategory().getId());

		//2. 遍历每一个属性 
		for (Property property : properties) {
			//2.1 根据属性和产品，获取属性值 
			PropertyValue propertyValue = selectByProductProperty(property.getId(), product.getId());
			//2.2如果属性值不存在，就创建一个属性值对象
			if (null == propertyValue) {
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				this.add(propertyValue);
			}
		}
	}

	/*
	 * 通过产品id查询
	 */
	/**   
	 * <p>Title: selectByProduct</p>   
	 * <p>Description: </p>   
	 * @param pid
	 * @return   
	 * @see com.tianma.dao.PropertyValueDao#selectByProduct(int)   
	 */
	@Override
	public List<PropertyValue> selectByProduct(int pid) {
		List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

		String sql = "select * from propertyValue where pid = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				propertyValue = new PropertyValue();
				int id = rs.getInt("id");

				int ptid = rs.getInt("ptid");
				String value = rs.getString("value");

				Product product = new ProductDaoImpl().selectById(pid);
				Property property = new PropertyDaoImpl().selectById(ptid);
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValue.setValue(value);
				propertyValue.setId(id);
				propertyValues.add(propertyValue);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return propertyValues;
	}

}
