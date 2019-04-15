package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.PropertyDao;
import com.tianma.dao.impl.PropertyDaoImpl;
import com.tianma.pojo.Property;
import com.tianma.service.PropertyService;

public class PropertyServiceImpl implements PropertyService {

	private PropertyDao propertyDao = null;
	
	/*
	 * 获取某种分类下属性总数（在分页显示的时候会用到）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#getTotal(int)
	 */
	@Override
	public int getTotal(int cid) {
		propertyDao = new PropertyDaoImpl();
		return propertyDao.getTotal(cid);
	}

	/*
	 * 增加
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#add(com.tianma.pojo.Property)
	 */
	@Override
	public void add(Property property) {
		propertyDao = new PropertyDaoImpl();
		propertyDao.add(property);
	}

	/*
	 * 修改
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#update(com.tianma.pojo.Property)
	 */
	@Override
	public void update(Property property) {
		propertyDao = new PropertyDaoImpl();
		propertyDao.update(property);
	}

	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#delete(int)
	 */
	@Override
	public void delete(int id) {
		propertyDao = new PropertyDaoImpl();
		propertyDao.delete(id);
	}

	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#selectById(int)
	 */
	@Override
	public Property selectById(int id) {
		propertyDao = new PropertyDaoImpl();
		return propertyDao.selectById(id);
	}

	/*
	 * 查询某个分类下的所有属性（调用分页查询所有方法）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#selectAll(int)
	 */
	@Override
	public List<Property> selectAll(int cid) {
		propertyDao = new PropertyDaoImpl();
		return propertyDao.selectAll(cid);
	}

	/*
	 * 分页查询某个分类下的所有属性
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.PropertyService#selectAll(int, int, int)
	 */
	@Override
	public List<Property> selectAll(int cid, int start, int count) {
		propertyDao = new PropertyDaoImpl();
		return propertyDao.selectAll(cid, start, count);
	}

	
}
