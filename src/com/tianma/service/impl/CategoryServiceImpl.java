package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.CategoryDao;
import com.tianma.dao.impl.CategoryDaoImpl;
import com.tianma.pojo.Category;
import com.tianma.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = null;
	
	/*
	 * 获取所有分类总数
	 */
	/**   
	 * <p>Title: getTotal</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.service.CategoryService#getTotal()   
	 */
	@Override
	public int getTotal() {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.getTotal();
	}

	/*
	 * 增加
	 */
	/**   
	 * <p>Title: add</p>   
	 * <p>Description: </p>   
	 * @param category   
	 * @see com.tianma.service.CategoryService#add(com.tianma.pojo.Category)   
	 */
	@Override
	public void add(Category category) {
		categoryDao = new CategoryDaoImpl();
		categoryDao.add(category);
	}

	/*
	 * 修改
	 */
	/**   
	 * <p>Title: update</p>   
	 * <p>Description: </p>   
	 * @param category   
	 * @see com.tianma.service.CategoryService#update(com.tianma.pojo.Category)   
	 */
	@Override
	public void update(Category category) {
		categoryDao = new CategoryDaoImpl();
		categoryDao.update(category);
	}

	/*
	 * 删除
	 */
	/**   
	 * <p>Title: delete</p>   
	 * <p>Description: </p>   
	 * @param id   
	 * @see com.tianma.service.CategoryService#delete(int)   
	 */
	@Override
	public void delete(int id) {
		categoryDao = new CategoryDaoImpl();
		categoryDao.delete(id);
	}

	/*
	 * 通过id查询
	 */
	/**   
	 * <p>Title: selectById</p>   
	 * <p>Description: </p>   
	 * @param id
	 * @return   
	 * @see com.tianma.service.CategoryService#selectById(int)   
	 */
	@Override
	public Category selectById(int id) {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.selectById(id);
	}

	/*
	 * 查询所有（调用分页查询所有方法）
	 */
	/**   
	 * <p>Title: selectAll</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see com.tianma.service.CategoryService#selectAll()   
	 */
	@Override
	public List<Category> selectAll() {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.selectAll();
	}

	/*
	 * 分页查询所有
	 */
	/**   
	 * <p>Title: selectAll</p>   
	 * <p>Description: </p>   
	 * @param start
	 * @param count
	 * @return   
	 * @see com.tianma.service.CategoryService#selectAll(int, int)   
	 */
	@Override
	public List<Category> selectAll(int start, int count) {
		categoryDao = new CategoryDaoImpl();
		return categoryDao.selectAll(start, count);
	}

}
