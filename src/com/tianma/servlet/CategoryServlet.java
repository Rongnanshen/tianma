package com.tianma.servlet;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianma.pojo.Category;
import com.tianma.util.ImageUtil;
import com.tianma.util.Page;

/**
 * 1. 首先CategoryServlet继承了BaseBackServlet，而BaseBackServlet又继承了HttpServlet
2. 服务端跳转过来之后，会访问CategoryServlet的doGet()或者doPost()方法
3. 在访问doGet()或者doPost()之前，会访问service()方法
4. BaseBackServlet中重写了service() 方法，所以流程就进入到了service()中
 * @author 1599193
 *
 */
public class CategoryServlet extends BaseBackServlet {
	
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		
		String name= params.get("name");
		Category c = new Category();
		c.setName(name);
		categoryService.add(c);
		
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,c.getId()+".jpg");
		
		try {
			if(null!=is && 0!=is.available()){
			    try(FileOutputStream fos = new FileOutputStream(file)){
			        byte b[] = new byte[1024 * 1024];
			        int length = 0;
			        while (-1 != (length = is.read(b))) {
			            fos.write(b, 0, length);
			        }
			        fos.flush();
			        //通过如下代码，把文件保存为jpg格式
			        BufferedImage img = ImageUtil.change2jpg(file);
			        ImageIO.write(img, "jpg", file);		
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "@admin_category_selectAll";
	}

	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryService.delete(id);
		return "@admin_category_selectAll";
	}

	
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Category c = categoryService.selectById(id);
		request.setAttribute("c", c);
		return "admin/editCategory.jsp";		
	}

	
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		Map<String,String> params = new HashMap<>();
		InputStream is = super.parseUpload(request, params);
		String name= params.get("name");
		int id = Integer.parseInt(params.get("id"));

		Category c = new Category();
		c.setId(id);
		c.setName(name);
		categoryService.update(c);
		
		File  imageFolder= new File(request.getSession().getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder,c.getId()+".jpg");
		file.getParentFile().mkdirs();
		
		try {
			if(null!=is && 0!=is.available()){
			    try(FileOutputStream fos = new FileOutputStream(file)){
			        byte b[] = new byte[1024 * 1024];
			        int length = 0;
			        while (-1 != (length = is.read(b))) {
			            fos.write(b, 0, length);
			        }
			        fos.flush();
			        //通过如下代码，把文件保存为jpg格式
			        BufferedImage img = ImageUtil.change2jpg(file);
			        ImageIO.write(img, "jpg", file);		
			    }
			    catch(Exception e){
			    	e.printStackTrace();
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "@admin_category_selectAll";

	}

	
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Category> cs = categoryService.selectAll(page.getStart(),page.getCount());
		
		int total = categoryService.getTotal();
		page.setTotal(total);
		
		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);
		
		return "admin/listCategory.jsp";
	}
}
