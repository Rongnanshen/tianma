package com.tianma.service;

import java.util.List;

import com.tianma.pojo.Property;

public interface PropertyService {

	/*
	 * 获取某种分类下属性总数（在分页显示的时候会用到）
	 */
	int getTotal(int cid);

	/*
	 * 增加
	 */
	void add(Property property);

	/*
	 * 修改
	 */
	void update(Property property);

	/*
	 * 删除
	 */
	void delete(int id);

	/*
	 * 通过id查询
	 */
	Property selectById(int id);

	/*
	 * 查询某个分类下的所有属性（调用分页查询所有方法）
	 */
	List<Property> selectAll(int cid);

	/*
	 * 分页查询某个分类下的所有属性
	 */
	List<Property> selectAll(int cid, int start, int count);

}