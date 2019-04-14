package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianma.dao.ReviewDao;
import com.tianma.pojo.Product;
import com.tianma.pojo.Review;
import com.tianma.pojo.User;
import com.tianma.util.DBUtil;
import com.tianma.util.DateUtil;

public class ReviewDaoImpl implements ReviewDao {

	private Review review = null;
	
	/*
	 * 获取评论量总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#getTotal()
	 */
	@Override
	public int getTotal() {

		int total = 0;
		String sql = "select count(*) from Review";

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
	 * 获取某个产品的评论量
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#getTotal(int)
	 */
	@Override
	public int getTotal(int pid) {

		int total = 0;
		String sql = "select count(*) from review where pid = ?";

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

	/*
	 * 增加评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#add(com.tianma.pojo.Review)
	 */
	@Override
	public void add(Review review) {

		String sql = "insert into review(id,content,uid,pid,createDate) values(?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, review.getId());
			ps.setString(2, review.getContent());
			ps.setInt(3, review.getUser().getId());
			ps.setInt(4, review.getProduct().getId());
			ps.setTimestamp(5, DateUtil.d2t(review.getCreateDate()));

			ps.execute();

			/*ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				review.setId(id);
			}*/
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 修改评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#update(com.tianma.pojo.Review)
	 */
	@Override
	public void update(Review review) {

		String sql = "update review set content= ?, uid=?, pid=? , createDate = ? where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, review.getContent());
			ps.setInt(2, review.getUser().getId());
			ps.setInt(3, review.getProduct().getId());
			ps.setTimestamp(4, DateUtil.d2t(review.getCreateDate()));
			ps.setInt(5, review.getId());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#delete(int)
	 */
	@Override
	public void delete(int id) {

		String sql = "delete from review where id = ?";
		
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
	 * @see com.tianma.dao.impl.ReviewDao#get(int)
	 */
	@Override
	public Review selectById(int id) {
		
		review = new Review();
		String sql = "select * from review where id = ?";
		
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				int pid = rs.getInt("pid");
				int uid = rs.getInt("uid");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

				String content = rs.getString("content");

				Product product = new ProductDaoImpl().selectById(pid);
				User user = new UserDaoImpl().selectById(uid);

				review.setContent(content);
				review.setCreateDate(createDate);
				review.setProduct(product);
				review.setUser(user);
				review.setId(id);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return review;
	}

	/*
	 * 查询某产品所有评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#selectAll(int)
	 */
	@Override
	public List<Review> selectAll(int pid) {
		return selectAll(pid, 0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询某个产品的所有评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#selectAll(int, int, int)
	 */
	@Override
	public List<Review> selectAll(int pid, int start, int count) {
		List<Review> reviews = new ArrayList<Review>();

		String sql = "select * from review where pid = ? limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, pid);
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				review = new Review();
				int id = rs.getInt("id");

				int uid = rs.getInt("uid");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

				String content = rs.getString("content");

				Product product = new ProductDaoImpl().selectById(pid);
				User user = new UserDaoImpl().selectById(uid);

				review.setContent(content);
				review.setCreateDate(createDate);
				review.setId(id);
				review.setProduct(product);
				review.setUser(user);
				reviews.add(review);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return reviews;
	}

	/*
	 * 在一个产品下根据评论内容查询，查询到返回true,查询不到返回false
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ReviewDao#isExist(java.lang.String, int)
	 */
	@Override
	public boolean isExist(String content, int pid) {

		String sql = "select * from review where content = ? and pid = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, content);
			ps.setInt(2, pid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

}
