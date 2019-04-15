package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.ReviewDao;
import com.tianma.dao.impl.ReviewDaoImpl;
import com.tianma.pojo.Review;
import com.tianma.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao = null;
	
	/*
	 * 获取评论量总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#getTotal()
	 */
	@Override
	public int getTotal() {
		reviewDao = new ReviewDaoImpl();
		return reviewDao.getTotal();
	}

	/*
	 * 获取某个产品的评论量
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#getTotal(int)
	 */
	@Override
	public int getTotal(int pid) {
		reviewDao = new ReviewDaoImpl();
		return reviewDao.getTotal(pid);
	}

	/*
	 * 增加评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#add(com.tianma.pojo.Review)
	 */
	@Override
	public void add(Review review) {
		reviewDao = new ReviewDaoImpl();
		reviewDao.add(review);
	}

	/*
	 * 修改评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#update(com.tianma.pojo.Review)
	 */
	@Override
	public void update(Review review) {
		reviewDao = new ReviewDaoImpl();
		reviewDao.update(review);
	}

	/*
	 * 删除评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#delete(int)
	 */
	@Override
	public void delete(int id) {
		reviewDao = new ReviewDaoImpl();
		reviewDao.delete(id);
	}

	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#selectById(int)
	 */
	@Override
	public Review selectById(int id) {
		reviewDao = new ReviewDaoImpl();
		return reviewDao.selectById(id);
	}

	/*
	 * 查询某产品所有评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#selectAll(int)
	 */
	@Override
	public List<Review> selectAll(int pid) {
		reviewDao = new ReviewDaoImpl();
		return reviewDao.selectAll(pid);
	}

	/*
	 * 分页查询某个产品的所有评论
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#selectAll(int, int, int)
	 */
	@Override
	public List<Review> selectAll(int pid, int start, int count) {
		reviewDao = new ReviewDaoImpl();
		return reviewDao.selectAll(pid, start, count);
	}

	/*
	 * 在一个产品下根据评论内容查询，查询到返回true,查询不到返回false
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ReviewService#isExist(java.lang.String, int)
	 */
	@Override
	public boolean isExist(String content, int pid) {
		reviewDao = new ReviewDaoImpl();
		return reviewDao.isExist(content, pid);
	}

	
}
