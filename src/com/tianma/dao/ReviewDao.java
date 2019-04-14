package com.tianma.dao;

import java.util.List;

import com.tianma.pojo.Review;

public interface ReviewDao {

	/*
	 * 获取评论量总数
	 */
	int getTotal();

	/*
	 * 获取某个产品的评论量
	 */
	int getTotal(int pid);

	/*
	 * 增加评论
	 */
	void add(Review review);

	/*
	 * 修改评论
	 */
	void update(Review review);

	/*
	 * 删除评论
	 */
	void delete(int id);

	/*
	 * 通过id查询
	 */
	Review selectById(int id);

	/*
	 * 查询某产品所有评论
	 */
	List<Review> selectAll(int pid);

	/*
	 * 分页查询某个产品的所有评论
	 */
	List<Review> selectAll(int pid, int start, int count);

	/*
	 * 在一个产品下根据评论内容查询，查询到返回true,查询不到返回false
	 */
	boolean isExist(String content, int pid);

}