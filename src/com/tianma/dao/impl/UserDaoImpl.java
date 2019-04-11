package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianma.dao.UserDao;
import com.tianma.pojo.Category;
import com.tianma.pojo.User;
import com.tianma.util.DBUtil;

public class UserDaoImpl implements UserDao {

	private User user;

	/*
	 * 获取用户总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#getTotal()
	 */
	@Override
	public int getTotal() {

		int total = 0;
		String sql = "select count(*) from user";

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
	 * @see com.tianma.dao.impl.UserDao#add(com.tianma.pojo.User)
	 */
	@Override
	public void add(User user) {

		String sql = "insert into user(id,name,mobile,password,role) values(?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getMobile());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getRole());

			ps.execute();
			// 获取主键id
			/* ResultSet rs = ps.getGeneratedKeys(); */

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 修改
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#update(com.tianma.pojo.User)
	 */
	@Override
	public void update(User user) {

		String sql = "update user set name=?,mobile=?,password=?,role=? where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, user.getName());
			ps.setString(2, user.getMobile());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRole());
			ps.setInt(5, user.getId());

			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#delete(int)
	 */
	@Override
	public void delete(int id) {

		String sql = "delete from user where id = ?";
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
	 * @see com.tianma.dao.impl.UserDao#selectById(int)
	 */
	@Override
	public User selectById(int id) {

		String sql = "select * from user where id = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();

				user.setId(id);
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;

	}

	/*
	 * 查询所有（调用分页查询所有方法）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#selectAll()
	 */
	@Override
	public List<User> selectAll() {

		return selectAll(0, Short.MAX_VALUE);

	}

	/*
	 * 分页查询所有
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#selectAll(int, int)
	 */
	@Override
	public List<User> selectAll(int start, int count) {

		List<User> users = new ArrayList<User>();

		String sql = "select * from user limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				users.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;

	}
	
	/*
	 * 判断账号是否已经存在
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String mobile) {
        
		User user = selectByMobile(mobile);
        return user!=null;
 
    }
	
	/*
	 * 通过手机号查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#selectByMobile(java.lang.String)
	 */
	@Override
	public User selectByMobile(String mobile) {
        
		User user = null;
          
        String sql = "select * from user where mobile = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, mobile);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setMobile(mobile);
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return user;
    }
	
	/*
	 * 登录验证
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.UserDao#get(java.lang.String, java.lang.String)
	 */
	@Override
	public User get(String mobile, String password) {
        
        String sql = "select * from user where mobile = ? and password=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, mobile);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setMobile(mobile);
				user.setPassword(password);
				user.setRole(rs.getString("role"));
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return user;
    }

}
