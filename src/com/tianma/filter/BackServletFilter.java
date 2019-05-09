package com.tianma.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class BackServletFilter implements Filter {

	public void destroy() {
		
	}

	/*
	 * 1.假设访问路径是 http://127.0.0.1:8080/tmall/admin_category_selectAll
2. 过滤器BackServletFilter进行拦截，判断访问的地址是否以/admin_开头
3. 如果是，那么做如下操作
3.1 取出两个下划线之间的值 category
3.2 取出最后一个下划线之后的值 selectAll
3.3 然后根据这个值，服务端跳转到categoryServlet，并且把selectAll这个值传递过去
4. categoryServlet 继承了BaseBackServlet，其service方法会被调用。 在service中，借助反射技术，根据传递过来的值 selectAll，调用对应categoryServlet 中的方法selectAll()
5. 这样就实现了当访问的路径是 admin_category_selectAll的时候，就会调用categoryServlet.selectAll()方法这样一个效果
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String contextPath=request.getServletContext().getContextPath();
		String uri = request.getRequestURI();
		uri =StringUtils.remove(uri, contextPath);
		if(uri.startsWith("/admin_")){		
			String servletPath = StringUtils.substringBetween(uri,"_", "_") + "Servlet";
			String method = StringUtils.substringAfterLast(uri,"_" );
			request.setAttribute("method", method);
			req.getRequestDispatcher("/" + servletPath).forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig arg0) throws ServletException {
	
	}
}