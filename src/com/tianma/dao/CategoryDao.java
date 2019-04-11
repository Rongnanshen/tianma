package com.tianma.dao;

import java.util.List;

import com.tianma.pojo.Category;

public interface CategoryDao {

	/*
	 * 获取所有分类总数
	 */
	int getTotal();

	/*
	 * 增加
	 */
	void add(Category category);

	/*
	 * 修改
	 */
	void update(Category category);

	/*
	 * 删除
	 */
	void delete(int id);

	/*
	 * 通过id查询
	 */
	Category selectById(int id);

	/*
	 * 查询所有（调用分页查询所有方法）
	 */
	List<Category> selectAll();

	/*
	 * 分页查询所有
	 */
	List<Category> selectAll(int start, int count);

}