package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.PropertyValueDao;
import com.tianma.dao.impl.PropertyValueDaoImpl;
import com.tianma.pojo.Product;
import com.tianma.pojo.PropertyValue;
import com.tianma.service.PropertyValueService;

public class PropertyValueServiceImpl implements PropertyValueService {

	private PropertyValueDao propertyValueDao = null;
	
	/*
	 * 获取属性值总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#getTotal()
	 */
	@Override
	public int getTotal() {
		propertyValueDao = new PropertyValueDaoImpl();
		return propertyValueDao.getTotal();
	}

	/*
	 * 增加属性值
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#add(com.tianma.pojo.PropertyValue)
	 */
	@Override
	public void add(PropertyValue propertyValue) {
		propertyValueDao = new PropertyValueDaoImpl();
		propertyValueDao.add(propertyValue);
	}

	/*
	 * 修改属性值
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#update(com.tianma.pojo.PropertyValue)
	 */
	@Override
	public void update(PropertyValue propertyValue) {
		propertyValueDao = new PropertyValueDaoImpl();
		propertyValueDao.update(propertyValue);
	}

	/*
	 * 删除属性值
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#delete(int)
	 */
	@Override
	public void delete(int id) {
		propertyValueDao = new PropertyValueDaoImpl();
		propertyValueDao.delete(id);
	}

	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#selectById(int)
	 */
	@Override
	public PropertyValue selectById(int id) {
		propertyValueDao = new PropertyValueDaoImpl();
		return propertyValueDao.selectById(id);
	}

	/*
	 * 通过属性id和产品id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#selectByProductProperty(int, int)
	 */
	@Override
	public PropertyValue selectByProductProperty(int ptid, int pid) {
		propertyValueDao = new PropertyValueDaoImpl();
		return propertyValueDao.selectByProductProperty(ptid, pid);
	}

	/*
	 * 查询所有属性值
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#selectAll()
	 */
	@Override
	public List<PropertyValue> selectAll() {
		propertyValueDao = new PropertyValueDaoImpl();
		return propertyValueDao.selectAll();
	}

	/*
	 * 分页查询所有属性值
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#selectAll(int, int)
	 */
	@Override
	public List<PropertyValue> selectAll(int start, int count) {
		propertyValueDao = new PropertyValueDaoImpl();
		return propertyValueDao.selectAll(start, count);
	}

	/*
	 * 初始化某个产品的属性值： 
	 * 1. 根据分类获取所有的属性 
	 * 2. 遍历每一个属性 
	 * 2.1 根据属性和产品，获取属性值 
	 * 2.2如果属性值不存在，就创建一个属性值对象
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#init(com.tianma.pojo.Product)
	 */
	@Override
	public void init(Product product) {
		propertyValueDao = new PropertyValueDaoImpl();
		propertyValueDao.init(product);
	}

	/*
	 * 通过产品id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyValueService#selectByProduct(int)
	 */
	@Override
	public List<PropertyValue> selectByProduct(int pid) {
		propertyValueDao = new PropertyValueDaoImpl();
		return propertyValueDao.selectByProduct(pid);
	}

	
}
