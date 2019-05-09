
package com.tianma.servlet;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianma.service.CategoryService;
import com.tianma.service.OrderItemService;
import com.tianma.service.OrderService;
import com.tianma.service.ProductImageService;
import com.tianma.service.ProductService;
import com.tianma.service.PropertyService;
import com.tianma.service.PropertyValueService;
import com.tianma.service.ReviewService;
import com.tianma.service.UserService;
import com.tianma.service.impl.CategoryServiceImpl;
import com.tianma.service.impl.OrderItemServiceImpl;
import com.tianma.service.impl.OrderServiceImpl;
import com.tianma.service.impl.ProductImageServiceImpl;
import com.tianma.service.impl.ProductServiceImpl;
import com.tianma.service.impl.PropertyServiceImpl;
import com.tianma.service.impl.PropertyValueServiceImpl;
import com.tianma.service.impl.ReviewServiceImpl;
import com.tianma.service.impl.UserServiceImpl;
import com.tianma.util.Page;

public class BaseForeServlet extends HttpServlet{

	protected CategoryService categoryService = new CategoryServiceImpl();
	protected OrderService orderService = new OrderServiceImpl();
	protected OrderItemService orderItemService = new OrderItemServiceImpl();
	protected ProductService productService = new ProductServiceImpl();
	protected ProductImageService productImageService = new ProductImageServiceImpl();
	protected PropertyService propertyService = new PropertyServiceImpl();
	protected PropertyValueService propertyValueService = new PropertyValueServiceImpl();
	protected ReviewService reviewService = new ReviewServiceImpl();
	protected UserService userService = new UserServiceImpl();
	
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int start= 0;
			int count = 10;
			try {
				start = Integer.parseInt(request.getParameter("page.start"));
			} catch (Exception e) {
				
			}
			
			try {
				count = Integer.parseInt(request.getParameter("page.count"));
			} catch (Exception e) {
			}
			
			Page page = new Page(start,count);
			
			String method = (String) request.getAttribute("method");

			Method m = this.getClass().getMethod(method, javax.servlet.http.HttpServletRequest.class,
					javax.servlet.http.HttpServletResponse.class,Page.class);
			
			String redirect = m.invoke(this,request, response,page).toString();
			
			if(redirect.startsWith("@"))
				response.sendRedirect(redirect.substring(1));
			else if(redirect.startsWith("%"))
				response.getWriter().print(redirect.substring(1));
			else
				request.getRequestDispatcher(redirect).forward(request, response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

