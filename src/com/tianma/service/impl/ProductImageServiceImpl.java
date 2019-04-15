package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.ProductImageDao;
import com.tianma.dao.impl.ProductImageDaoImpl;
import com.tianma.pojo.Product;
import com.tianma.pojo.ProductImage;
import com.tianma.service.ProductImageService;

public class ProductImageServiceImpl implements ProductImageService {

	private ProductImageDao productImageDao = null;
	
	/*
	 * 获取产品图片总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#getTotal()
	 */
	@Override
	public int getTotal() {
		productImageDao = new ProductImageDaoImpl();
		return productImageDao.getTotal();
	}

	/*
	 * 增加
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#add(com.tianma.pojo.ProductImage)
	 */
	@Override
	public void add(ProductImage productImage) {
		productImageDao = new ProductImageDaoImpl();
		productImageDao.add(productImage);
	}

	/*
	 * 修改
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#update(com.tianma.pojo.ProductImage)
	 */
	@Override
	public void update(ProductImage bean) {
		productImageDao = new ProductImageDaoImpl();
		productImageDao.update(bean);
	}

	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#delete(int)
	 */
	@Override
	public void delete(int id) {
		productImageDao = new ProductImageDaoImpl();
		productImageDao.delete(id);
	}

	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#selectById(int)
	 */
	@Override
	public ProductImage selectById(int id) {
		productImageDao = new ProductImageDaoImpl();
		return productImageDao.selectById(id);
	}

	/*
	 * 查询某产品的所有图片（调用分页查询所有图片方法）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#selectAll(com.tianma.pojo.Product, java.lang.String)
	 */
	@Override
	public List<ProductImage> selectAll(Product product, String type) {
		productImageDao = new ProductImageDaoImpl();
		return productImageDao.selectAll(product, type);
	}

	/*
	 * 分页查询某产品的某个类型的所有图片
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.ProductImageService#selectAll(com.tianma.pojo.Product, java.lang.String, int, int)
	 */
	@Override
	public List<ProductImage> selectAll(Product product, String type, int start, int count) {
		productImageDao = new ProductImageDaoImpl();
		return productImageDao.selectAll(product, type, start, count);
	}

	
}
