package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianma.dao.CategoryDao;
import com.tianma.pojo.Category;
import com.tianma.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

	private Category category = null;
	
	/*
	 * 获取所有分类总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.CategoryDao#getTotal()
	 */
	@Override
	public int getTotal() {
		
		int total = 0;
		String sql = "select count(*) from category";
		
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ResultSet rs = ps.executeQuery(sql);
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
	 * @see com.tianma.dao.impl.CategoryDao#add(com.tianma.pojo.Category)
	 */
	@Override
	public void add(Category category) {
		
		String sql = "insert into category(id,name) values(?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, category.getId());
            ps.setString(2, category.getName());
  
            ps.execute();
            // 获取主键id
            /*ResultSet rs = ps.getGeneratedKeys();*/
            
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        
	}
	
	/*
	 * 修改
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.CategoryDao#update(com.tianma.pojo.Category)
	 */
	@Override
	public void update(Category category) {
		
		String sql = "update category set name= ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
  
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
		
	}
	
	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.CategoryDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		
		String sql = "delete from category where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			  
			ps.setInt(1, id);
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
		
	}
	
	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.CategoryDao#selectById(int)
	 */
	@Override
	public Category selectById(int id) {
		
		String sql = "select * from category where id = ?";
		
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ResultSet rs = ps.executeQuery();
  
            if (rs.next()) {
            	category = new Category();
                category.setName(rs.getString("name"));
                category.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return category;
		
	}
	
	/*
	 * 查询所有（调用分页查询所有方法）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.CategoryDao#selectAll()
	 */
	@Override
	public List<Category> selectAll() {
		
		return selectAll(0, Short.MAX_VALUE);
		
	}
	
	/*
	 * 分页查询所有
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.CategoryDao#selectAll(int, int)
	 */
	@Override
	public List<Category> selectAll(int start, int count) {
		
		List<Category> categories = new ArrayList<Category>();
		  
        String sql = "select * from category limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                
            	category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return categories;
		
	}
	
}
