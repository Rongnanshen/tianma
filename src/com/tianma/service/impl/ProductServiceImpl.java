package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.ProductDao;
import com.tianma.dao.impl.ProductDaoImpl;
import com.tianma.pojo.Category;
import com.tianma.pojo.Product;
import com.tianma.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = null;
	
	/*
	 * 获取某分类下的产品总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#getTotal(int)
	 */
	@Override
	public int getTotal(int cid) {
		productDao = new ProductDaoImpl();
		return productDao.getTotal(cid);
	}

	/*
	 * 增加产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#add(com.tianma.pojo.Product)
	 */
	@Override
	public void add(Product product) {
		productDao = new ProductDaoImpl();
		productDao.add(product);
	}

	/*
	 * 修改产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#update(com.tianma.pojo.Product)
	 */
	@Override
	public void update(Product product) {
		productDao = new ProductDaoImpl();
		productDao.update(product);
	}

	/*
	 * 删除产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#delete(int)
	 */
	@Override
	public void delete(int id) {
		productDao = new ProductDaoImpl();
		productDao.delete(id);
	}

	/*
	 * 通过id查询产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#selectById(int)
	 */
	@Override
	public Product selectById(int id) {
		productDao = new ProductDaoImpl();
		return productDao.selectById(id);
	}

	/*
	 * 查询某分类下的所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#selectAll(int)
	 */
	@Override
	public List<Product> selectAll(int cid) {
		productDao = new ProductDaoImpl();
		return productDao.selectAll(cid);
	}

	/*
	 * 分页查询某分类下的所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#selectAll(int, int, int)
	 */
	@Override
	public List<Product> selectAll(int cid, int start, int count) {
		productDao = new ProductDaoImpl();
		return productDao.selectAll(cid, start, count);
	}

	/*
	 * 查询所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#selectAll()
	 */
	@Override
	public List<Product> selectAll() {
		productDao = new ProductDaoImpl();
		return productDao.selectAll();
	}

	/*
	 * 分页查询所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#selectAll(int, int)
	 */
	@Override
	public List<Product> selectAll(int start, int count) {
		productDao = new ProductDaoImpl();
		return productDao.selectAll(start, count);
	}

	/*
	 * 遍历每个分类为分类填充产品集合
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#fill(java.util.List)
	 */
	@Override
	public void fill(List<Category> categories) {
		productDao = new ProductDaoImpl();
		productDao.fill(categories);
	}

	/*
	 * 为一个分类填充产品集合
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#fill(com.tianma.pojo.Category)
	 */
	@Override
	public void fill(Category category) {
		productDao = new ProductDaoImpl();
		productDao.fill(category);
	}

	/*
	 * 为了页面上方便显示，将一个产品集合拆分成几个小集合按行显示
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#fillByRow(java.util.List)
	 */
	@Override
	public void fillByRow(List<Category> categories) {
		productDao = new ProductDaoImpl();
		productDao.fillByRow(categories);
	}

	/*
	 * 将该产品的第一张图片设置为主图片
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#setFirstProductImage(com.tianma.pojo.Product)
	 */
	@Override
	public void setFirstProductImage(Product product) {
		productDao = new ProductDaoImpl();
		productDao.setFirstProductImage(product);
	}

	/*
	 * 为单个产品设置销售量和评价量
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#setSaleAndReviewNumber(com.tianma.pojo.Product)
	 */
	@Override
	public void setSaleAndReviewNumber(Product product) {
		productDao = new ProductDaoImpl();
		productDao.setSaleAndReviewNumber(product);
	}

	/*
	 * 为一个产品集合设置销售量和评价量
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#setSaleAndReviewNumber(java.util.List)
	 */
	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		productDao = new ProductDaoImpl();
		productDao.setSaleAndReviewNumber(products);
	}

	/*
	 * 根据关键字分页查询产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductService#search(java.lang.String, int, int)
	 */
	@Override
	public List<Product> search(String keyword, int start, int count) {
		productDao = new ProductDaoImpl();
		return productDao.search(keyword, start, count);
	}

	
}
