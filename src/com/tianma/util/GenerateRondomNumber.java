package com.tianma.util;

import java.util.Random;

import com.tianma.dao.CategoryDao;
import com.tianma.dao.OrderDao;
import com.tianma.dao.OrderItemDao;
import com.tianma.dao.ProductDao;
import com.tianma.dao.ProductImageDao;
import com.tianma.dao.PropertyDao;
import com.tianma.dao.PropertyValueDao;
import com.tianma.dao.ReviewDao;
import com.tianma.dao.UserDao;
import com.tianma.dao.impl.CategoryDaoImpl;
import com.tianma.dao.impl.OrderDaoImpl;
import com.tianma.dao.impl.OrderItemDaoImpl;
import com.tianma.dao.impl.ProductDaoImpl;
import com.tianma.dao.impl.ProductImageDaoImpl;
import com.tianma.dao.impl.PropertyDaoImpl;
import com.tianma.dao.impl.PropertyValueDaoImpl;
import com.tianma.dao.impl.ReviewDaoImpl;
import com.tianma.dao.impl.UserDaoImpl;
import com.tianma.pojo.Category;
import com.tianma.pojo.Order;
import com.tianma.pojo.OrderItem;
import com.tianma.pojo.Product;
import com.tianma.pojo.ProductImage;
import com.tianma.pojo.Property;
import com.tianma.pojo.PropertyValue;
import com.tianma.pojo.Review;
import com.tianma.pojo.User;


public class GenerateRondomNumber {

	/*
	 * 生成8位不重复随机数
	 */
	public int getRondom(String dao) {
		
		Random random = new Random();
		//使用StringBuilder而不用String是因为在遍历生成随机数时会多次创建同一个String字符，导致内存占用，速度变慢
		StringBuilder stb = new StringBuilder();//定义变长字符串
		for (int i = 0; i < 8; i++) {
			//随机获取1-10的一个整数，并遍历拼接成为一个8个字符的随机字符串
			stb.append(random.nextInt(10));
		}
		//将8个字符的字符串转化为8位数整数
		int num = Integer.parseInt(stb.toString());
		//通过id查询
		if ("UserDao".equals(dao)) {
			
			UserDao userDao = new UserDaoImpl();
			User user = userDao.selectById(num);
			if (user != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("CategoryDao".equals(dao)) {
			
			CategoryDao categoryDao = new CategoryDaoImpl();
			Category category = categoryDao.selectById(num);
			if (category != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("OrderDao".equals(dao)) {
			
			OrderDao orderDao = new OrderDaoImpl();
			Order order = orderDao.selectById(num);
			if (order != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("OrderItemDao".equals(dao)) {
			
			OrderItemDao orderItemDao = new OrderItemDaoImpl();
			OrderItem orderItem = orderItemDao.selectById(num);
			if (orderItem != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("ProductDao".equals(dao)) {
			
			ProductDao productDao = new ProductDaoImpl();
			Product product = productDao.selectById(num);
			if (product != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("ProductImageDao".equals(dao)) {
			
			ProductImageDao productImageDao = new ProductImageDaoImpl();
			ProductImage productImage = productImageDao.selectById(num);
			if (productImage != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("PropertyDao".equals(dao)) {
			
			PropertyDao propertyDao = new PropertyDaoImpl();
			Property property = propertyDao.selectById(num);
			if (property != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("PropertyValueDao".equals(dao)) {
			
			PropertyValueDao propertyValueDao = new PropertyValueDaoImpl();
			PropertyValue propertyValue = propertyValueDao.selectById(num);
			if (propertyValue != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else if ("ReviewDao".equals(dao)) {
			
			ReviewDao reviewDao = new ReviewDaoImpl();
			Review review = reviewDao.selectById(num);
			if (review != null) {
				return getRondom(dao);
			}else {
				return num;
			}
			
		}else {
			return 00000000;
		}
	}
	
}
