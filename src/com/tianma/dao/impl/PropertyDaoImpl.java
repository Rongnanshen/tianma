package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianma.dao.PropertyDao;
import com.tianma.pojo.Category;
import com.tianma.pojo.Property;
import com.tianma.util.DBUtil;

public class PropertyDaoImpl implements PropertyDao {

	private Property property = null;
	/*
	 * 获取某种分类下属性总数（在分页显示的时候会用到）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#getTotal(int)
	 */
	@Override
	public int getTotal(int cid) {
		int total = 0;
		String sql = "select count(*) from Property where cid =?";
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
	 * 增加
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#add(com.tianma.pojo.Property)
	 */
	@Override
	public void add(Property property) {

		String sql = "insert into property(id,cid,name) values(?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, property.getId());
			ps.setInt(2, property.getCategory().getId());
			ps.setString(3, property.getName());
			ps.execute();

			/*ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				property.setId(id);
			}*/
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 修改
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#update(com.tianma.pojo.Property)
	 */
	@Override
	public void update(Property property) {

		String sql = "update property set cid= ?, name=? where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, property.getCategory().getId());
			ps.setString(2, property.getName());
			ps.setInt(3, property.getId());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#delete(int)
	 */
	@Override
	public void delete(int id) {

		String sql = "delete from Property where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#selectById(int)
	 */
	@Override
	public Property selectById(int id) {
		
		property = new Property();
		String sql = "select * from Property where id = ?";
		
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int cid = rs.getInt("cid");
				property.setId(id);
				property.setName(rs.getString("name"));
				Category category = new CategoryDaoImpl().selectById(cid);
				property.setCategory(category);
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return property;
	}

	/*
	 * 查询某个分类下的所有属性（调用分页查询所有方法）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#selectAll(int)
	 */
	@Override
	public List<Property> selectAll(int cid) {
		return selectAll(cid, 0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询某个分类下的所有属性
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.PropertyDao#selectAll(int, int, int)
	 */
	@Override
	public List<Property> selectAll(int cid, int start, int count) {
		List<Property> properties = new ArrayList<Property>();

		String sql = "select * from property where cid = ? limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				property = new Property();
				int id = rs.getInt(1);
				String name = rs.getString("name");
				property.setId(id);
				property.setName(name);
				Category category = new CategoryDaoImpl().selectById(cid);
				property.setCategory(category);

				properties.add(property);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return properties;
	}

}
