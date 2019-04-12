package com.tianma.dao;

import java.util.List;

import com.tianma.pojo.Category;
import com.tianma.pojo.Product;

public interface ProductDao {

	/*
	 * 获取某分类下的产品总数
	 */
	int getTotal(int cid);

	/*
	 * 增加产品
	 */
	void add(Product product);

	/*
	 * 修改产品
	 */
	void update(Product product);

	/*
	 * 删除产品
	 */
	void delete(int id);

	/*
	 * 通过id查询产品
	 */
	Product selectById(int id);

	/*
	 * 查询某分类下的所有产品
	 */
	List<Product> selectAll(int cid);

	/*
	 * 分页查询某分类下的所有产品
	 */
	List<Product> selectAll(int cid, int start, int count);

	/*
	 * 查询所有产品
	 */
	List<Product> selectAll();

	/*
	 * 分页查询所有产品
	 */
	List<Product> selectAll(int start, int count);

	/*
	 * 遍历每个分类为分类填充产品集合
	 */
	void fill(List<Category> categories);

	/*
	 * 为一个分类填充产品集合
	 */
	void fill(Category category);

	/*
	 * 为了页面上方便显示，将一个产品集合拆分成几个小集合按行显示
	 */
	void fillByRow(List<Category> categories);

	/*
	 * 将该产品的第一张图片设置为主图片
	 */
	void setFirstProductImage(Product product);

	/*
	 * 为单个产品设置销售量和评价量
	 */
	void setSaleAndReviewNumber(Product product);

	/*
	 * 为一个产品集合设置销售量和评价量
	 */
	void setSaleAndReviewNumber(List<Product> products);

	/*
	 * 根据关键字分页查询产品
	 */
	List<Product> search(String keyword, int start, int count);

}