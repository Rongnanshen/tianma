package com.tianma.service;

import java.util.List;

import com.tianma.pojo.Product;
import com.tianma.pojo.PropertyValue;

public interface PropertyValueService {

	/*
	 * 获取属性值总数
	 */
	int getTotal();

	/*
	 * 增加属性值
	 */
	void add(PropertyValue propertyValue);

	/*
	 * 修改属性值
	 */
	void update(PropertyValue propertyValue);

	/*
	 * 删除属性值
	 */
	void delete(int id);

	/*
	 * 通过id查询
	 */
	PropertyValue selectById(int id);

	/*
	 * 通过属性id和产品id查询
	 */
	PropertyValue selectByProductProperty(int ptid, int pid);

	/*
	 * 查询所有属性值
	 */
	List<PropertyValue> selectAll();

	/*
	 * 分页查询所有属性值
	 */
	List<PropertyValue> selectAll(int start, int count);

	/*
	 * 初始化某个产品的属性值： 
	 * 1. 根据分类获取所有的属性 
	 * 2. 遍历每一个属性 
	 * 2.1 根据属性和产品，获取属性值 
	 * 2.2如果属性值不存在，就创建一个属性值对象
	 */
	void init(Product product);

	/*
	 * 通过产品id查询
	 */
	List<PropertyValue> selectByProduct(int pid);

}