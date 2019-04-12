package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianma.dao.ProductDao;
import com.tianma.pojo.Category;
import com.tianma.pojo.Product;
import com.tianma.pojo.ProductImage;
import com.tianma.util.DBUtil;
import com.tianma.util.DateUtil;

public class ProductDaoImpl implements ProductDao {

	private Product product = null;

	/*
	 * 获取某分类下的产品总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#getTotal(int)
	 */
	@Override
	public int getTotal(int cid) {

		int total = 0;
		String sql = "select count(*) from product where cid = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return total;
	}

	/*
	 * 增加产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#add(com.tianma.pojo.Product)
	 */
	@Override
	public void add(Product product) {

		String sql = "insert into product(id,name,subTitle,orignalPrice,promotePrice,stock,cid,createDate) values(?,?,?,?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, product.getId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getSubTitle());
			ps.setFloat(4, product.getOrignalPrice());
			ps.setFloat(5, product.getPromotePrice());
			ps.setInt(6, product.getStock());
			ps.setInt(7, product.getCategory().getId());
			ps.setTimestamp(8, DateUtil.d2t(product.getCreateDate()));
			ps.execute();

			/*
			 * ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) { int id = rs.getInt(1);
			 * product.setId(id); }
			 */
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 修改产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#update(com.tianma.pojo.Product)
	 */
	@Override
	public void update(Product product) {

		String sql = "update product set name= ?, subTitle=?, orignalPrice=?,promotePrice=?,stock=?, cid = ?, createDate=? where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, product.getName());
			ps.setString(2, product.getSubTitle());
			ps.setFloat(3, product.getOrignalPrice());
			ps.setFloat(4, product.getPromotePrice());
			ps.setInt(5, product.getStock());
			ps.setInt(6, product.getCategory().getId());
			ps.setTimestamp(7, DateUtil.d2t(product.getCreateDate()));
			ps.setInt(8, product.getId());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * 删除产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#delete(int)
	 */
	@Override
	public void delete(int id) {

		String sql = "delete from product where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 通过id查询产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#selectById(int)
	 */
	@Override
	public Product selectById(int id) {

		product = new Product();
		String sql = "select * from product where id = ?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String name = rs.getString("name");
				String subTitle = rs.getString("subTitle");
				float orignalPrice = rs.getFloat("orignalPrice");
				float promotePrice = rs.getFloat("promotePrice");
				int stock = rs.getInt("stock");
				int cid = rs.getInt("cid");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

				product.setName(name);
				product.setSubTitle(subTitle);
				product.setOrignalPrice(orignalPrice);
				product.setPromotePrice(promotePrice);
				product.setStock(stock);
				Category category = new CategoryDaoImpl().selectById(cid);
				product.setCategory(category);
				product.setCreateDate(createDate);
				product.setId(id);
				setFirstProductImage(product);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return product;
	}

	/*
	 * 查询某分类下的所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#selectAll(int)
	 */
	@Override
	public List<Product> selectAll(int cid) {
		return selectAll(cid, 0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询某分类下的所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#selectAll(int, int, int)
	 */
	@Override
	public List<Product> selectAll(int cid, int start, int count) {
		List<Product> products = new ArrayList<Product>();
		Category category = new CategoryDaoImpl().selectById(cid);
		String sql = "select * from product where cid = ? limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				product = new Product();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String subTitle = rs.getString("subTitle");
				float orignalPrice = rs.getFloat("orignalPrice");
				float promotePrice = rs.getFloat("promotePrice");
				int stock = rs.getInt("stock");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

				product.setName(name);
				product.setSubTitle(subTitle);
				product.setOrignalPrice(orignalPrice);
				product.setPromotePrice(promotePrice);
				product.setStock(stock);
				product.setCreateDate(createDate);
				product.setId(id);
				product.setCategory(category);
				setFirstProductImage(product);
				products.add(product);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return products;
	}

	/*
	 * 查询所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#selectAll()
	 */
	@Override
	public List<Product> selectAll() {
		return selectAll(0, Short.MAX_VALUE);
	}

	/*
	 * 分页查询所有产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#selectAll(int, int)
	 */
	@Override
	public List<Product> selectAll(int start, int count) {
		List<Product> products = new ArrayList<Product>();

		String sql = "select * from product limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				product = new Product();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				String name = rs.getString("name");
				String subTitle = rs.getString("subTitle");
				float orignalPrice = rs.getFloat("orignalPrice");
				float promotePrice = rs.getFloat("promotePrice");
				int stock = rs.getInt("stock");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

				product.setName(name);
				product.setSubTitle(subTitle);
				product.setOrignalPrice(orignalPrice);
				product.setPromotePrice(promotePrice);
				product.setStock(stock);
				product.setCreateDate(createDate);
				product.setId(id);

				Category category = new CategoryDaoImpl().selectById(cid);
				product.setCategory(category);
				products.add(product);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return products;
	}

	/*
	 * 遍历每个分类为分类填充产品集合
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#fill(java.util.List)
	 */
	@Override
	public void fill(List<Category> categories) {
		for (Category category : categories)
			fill(category);
	}

	/*
	 * 为一个分类填充产品集合
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#fill(com.tianma.pojo.Category)
	 */
	@Override
	public void fill(Category category) {
		List<Product> products = this.selectAll(category.getId());
		category.setProducts(products);
	}

	/*
	 * 为了页面上方便显示，将一个产品集合拆分成几个小集合按行显示
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#fillByRow(java.util.List)
	 */
	@Override
	public void fillByRow(List<Category> categories) {

		int productNumberEachRow = 8;
		for (Category category : categories) {
			// 获取该分类下的所有产品集合
			List<Product> products = category.getProducts();

			List<List<Product>> productsByRow = new ArrayList<>();
			for (int i = 0; i < products.size(); i += productNumberEachRow) {
				int size = i + productNumberEachRow;
				size = size > products.size() ? products.size() : size;
				List<Product> productsOfEachRow = products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			category.setProductsByRow(productsByRow);
		}
	}

	/*
	 * 将该产品的第一张图片设置为主图片
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#setFirstProductImage(com.tianma.pojo.Product)
	 */
	@Override
	public void setFirstProductImage(Product product) {
		List<ProductImage> pis = new ProductImageDaoImpl().selectAll(product, ProductImageDaoImpl.type_single);
		if (!pis.isEmpty())
			product.setFirstProductImage(pis.get(0));
	}

	/*
	 * 为单个产品设置销售量和评价量
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#setSaleAndReviewNumber(com.tianma.pojo.Product)
	 */
	@Override
	public void setSaleAndReviewNumber(Product product) {
		int saleCount = new OrderItemDaoImpl().getSaleCount(product.getId());
		product.setSaleCount(saleCount);

		int reviewCount = new ReviewDaoImpl().getCount(product.getId());
		product.setReviewCount(reviewCount);

	}

	/*
	 * 为一个产品集合设置销售量和评价量
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#setSaleAndReviewNumber(java.util.List)
	 */
	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		for (Product p : products) {
			setSaleAndReviewNumber(p);
		}
	}

	/*
	 * 根据关键字分页查询产品
	 */
	/* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductDao#search(java.lang.String, int, int)
	 */
	@Override
	public List<Product> search(String keyword, int start, int count) {
		List<Product> products = new ArrayList<Product>();

		//trim():去掉字符串首尾的空格
		if (null == keyword || 0 == keyword.trim().length())
			return products;

		String sql = "select * from product where name like ? limit ?,? ";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, "%" + keyword.trim() + "%");
			ps.setInt(2, start);
			ps.setInt(3, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				product = new Product();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				String name = rs.getString("name");
				String subTitle = rs.getString("subTitle");
				float orignalPrice = rs.getFloat("orignalPrice");
				float promotePrice = rs.getFloat("promotePrice");
				int stock = rs.getInt("stock");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));

				product.setName(name);
				product.setSubTitle(subTitle);
				product.setOrignalPrice(orignalPrice);
				product.setPromotePrice(promotePrice);
				product.setStock(stock);
				product.setCreateDate(createDate);
				product.setId(id);

				Category category = new CategoryDaoImpl().selectById(cid);
				product.setCategory(category);
				setFirstProductImage(product);
				products.add(product);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return products;
	}

}
