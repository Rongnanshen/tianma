package com.tianma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianma.dao.ProductImageDao;
import com.tianma.pojo.Product;
import com.tianma.pojo.ProductImage;
import com.tianma.util.DBUtil;

public class ProductImageDaoImpl implements ProductImageDao {

	private ProductImage productImage = null;
	/*
	 * 获取产品图片总数
	 */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#getTotal()
	 */
    @Override
	public int getTotal() {
        
    	int total = 0;
    	String sql = "select count(*) from productImage";
    	
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
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
     * 增加
     */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#add(com.tianma.pojo.ProductImage)
	 */
    @Override
	public void add(ProductImage productImage) {

        String sql = "insert into productImage(id,pid,type) values(?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            
        	ps.setInt(1, productImage.getId());
        	ps.setInt(2, productImage.getProduct().getId());
            ps.setString(3, productImage.getType());
            ps.execute();
 
            /*ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                productImage.setId(id);
            }*/
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    /*
     * 修改
     */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#update(com.tianma.pojo.ProductImage)
	 */
    @Override
	public void update(ProductImage bean) {
 
    }
 
    /*
     * 删除
     */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#delete(int)
	 */
    @Override
	public void delete(int id) {
 
        String sql = "delete from ProductImage where id = ?";
        
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
        	ps.setInt(1, id);
            ps.execute();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    /*
     * 通过id查询
     */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#get(int)
	 */
    @Override
	public ProductImage selectById(int id) {
    	
        productImage = new ProductImage();
        String sql = "select * from ProductImage where id = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
        	ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                int pid = rs.getInt("pid");
                String type = rs.getString("type");
                Product product = new ProductDaoImpl().selectById(pid);
                productImage.setId(id);
                productImage.setProduct(product);
                productImage.setType(type);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return productImage;
    }
 
    /*
     * 查询某产品的所有图片（调用分页查询所有图片方法）
     */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#selectAll(com.tianma.pojo.Product, java.lang.String)
	 */
    @Override
	public List<ProductImage> selectAll(Product product, String type) {
        return selectAll(product, type,0, Short.MAX_VALUE);
    }
 
    /*
     * 分页查询某产品的某个类型的所有图片
     */
    /* (non-Javadoc)
	 * @see com.tianma.dao.impl.ProductImageDao#selectAll(com.tianma.pojo.Product, java.lang.String, int, int)
	 */
    @Override
	public List<ProductImage> selectAll(Product product, String type, int start, int count) {
        List<ProductImage> productImages = new ArrayList<ProductImage>();
 
        String sql = "select * from productImage where pid =? and type =? limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, product.getId());
            ps.setString(2, type);
 
            ps.setInt(3, start);
            ps.setInt(4, count);
            
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {

                productImage = new ProductImage();
                int id = rs.getInt(1);

                productImage.setId(id);
                productImage.setProduct(product);
                productImage.setType(type);
                  
                productImages.add(productImage);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return productImages;
    }
	
}
