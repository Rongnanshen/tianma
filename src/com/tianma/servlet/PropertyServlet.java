
package com.tianma.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianma.pojo.Category;
import com.tianma.pojo.Property;
import com.tianma.util.Page;

public class PropertyServlet extends BaseBackServlet {

	
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category c = categoryService.selectById(cid);
		
		String name= request.getParameter("name");
		Property p = new Property();
		p.setCategory(c);
		p.setName(name);
		propertyService.add(p);
		return "@admin_property_selectAll?cid="+cid;
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Property p = propertyService.selectById(id);
		propertyService.delete(id);
		return "@admin_property_selectAll?cid="+p.getCategory().getId();
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Property p = propertyService.selectById(id);
		request.setAttribute("p", p);
		return "admin/editProperty.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category c = categoryService.selectById(cid);

		
		int id = Integer.parseInt(request.getParameter("id"));
		String name= request.getParameter("name");
		Property p = new Property();
		p.setCategory(c);
		p.setId(id);
		p.setName(name);
		propertyService.update(p);
		return "@admin_property_selectAll?cid="+p.getCategory().getId();
	}

	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category c = categoryService.selectById(cid);
		List<Property> ps = propertyService.selectAll(cid, page.getStart(),page.getCount());
		int total = propertyService.getTotal(cid);
		page.setTotal(total);
		page.setParam("&cid="+c.getId());
		
		request.setAttribute("ps", ps);
		request.setAttribute("c", c);
		request.setAttribute("page", page);
		
		
		
		return "admin/listProperty.jsp";
	}
}
