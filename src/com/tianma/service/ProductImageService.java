package com.tianma.service;

import java.util.List;

import com.tianma.pojo.Product;
import com.tianma.pojo.ProductImage;

public interface ProductImageService {

	/*
	 * 获取产品图片总数
	 */
	int getTotal();

	/*
	 * 增加
	 */
	void add(ProductImage productImage);

	/*
	 * 修改
	 */
	void update(ProductImage bean);

	/*
	 * 删除
	 */
	void delete(int id);

	/*
	 * 通过id查询
	 */
	ProductImage selectById(int id);

	/*
	 * 查询某产品的所有图片（调用分页查询所有图片方法）
	 */
	List<ProductImage> selectAll(Product product, String type);

	/*
	 * 分页查询某产品的某个类型的所有图片
	 */
	List<ProductImage> selectAll(Product product, String type, int start, int count);

}