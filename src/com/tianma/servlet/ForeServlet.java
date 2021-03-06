
package com.tianma.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.util.HtmlUtils;

import com.tianma.comparator.ProductAllComparator;
import com.tianma.comparator.ProductDateComparator;
import com.tianma.comparator.ProductPriceComparator;
import com.tianma.comparator.ProductReviewComparator;
import com.tianma.comparator.ProductSaleCountComparator;
import com.tianma.dao.OrderDao;
import com.tianma.dao.ProductImageDao;
import com.tianma.pojo.Category;
import com.tianma.pojo.Order;
import com.tianma.pojo.OrderItem;
import com.tianma.pojo.Product;
import com.tianma.pojo.ProductImage;
import com.tianma.pojo.PropertyValue;
import com.tianma.pojo.Review;
import com.tianma.pojo.User;
import com.tianma.service.impl.CategoryServiceImpl;
import com.tianma.service.impl.OrderServiceImpl;
import com.tianma.service.impl.ProductServiceImpl;
import com.tianma.util.Page;
import com.tianma.util.Verification;

public class ForeServlet extends BaseForeServlet {

	public String home(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Category> cs = new CategoryServiceImpl().selectAll();
		new ProductServiceImpl().fill(cs);
		new ProductServiceImpl().fillByRow(cs);
		request.setAttribute("cs", cs);
		return "home.jsp";
	}

	/*
	 * 注册
	 */
	public String register(HttpServletRequest request, HttpServletResponse response, Page page) {
		// 1. 获取账号昵称密码
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		// 2. 通过HtmlUtils.htmlEscape(name);把账号里的特殊符号进行转义
		name = HtmlUtils.htmlEscape(name);
		mobile = HtmlUtils.htmlEscape(mobile);
		System.out.println(name);

		/*
		 * 3. 判断用户名是否存在 3.1 如果已经存在，就服务端跳转到reigster.jsp，并且带材错误提示信息 3.2
		 * 如果不存在，则加入到数据库中，并服务端跳转到registerSuccess.jsp页面
		 */
		boolean existName = userService.isExistName(name);
		boolean existMobile = userService.isExistMobile(mobile);

		if (existName) {
			request.setAttribute("msg", "昵称已经被使用,不能使用");
			return "register.jsp";
		}

		if (existMobile) {
			request.setAttribute("msg", "账号已经被注册,请重新输入");
			return "register.jsp";
		}

		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setPassword(password);
		System.out.println(user.getName());
		System.out.println(user.getMobile());
		System.out.println(user.getPassword());

		// 调用增加方法，实现用户注册
		userService.add(user);

		return "@registerSuccess.jsp";
	}

	/*
	 * 验证码
	 */
	public void verificationCode(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		// 随机生成字符串，并写入session
		String code = Verification.getRandCode(4);
		session.setAttribute("verification", code);
		BufferedImage image = Verification.getImage(100, 30, code, 5);
		response.setContentType("image/png");

		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(Verification.getByteArray(image));
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/*
	 * 登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response, Page page) {
		String name = request.getParameter("name");
		name = HtmlUtils.htmlEscape(name);
		String password = request.getParameter("password");

		User user = userService.login(name, password);

		if (null == user) {
			request.setAttribute("msg", "账号密码错误");
			return "login.jsp";
		}
		request.getSession().setAttribute("user", user);
		return "@forehome";
	}

	public String product(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		Product p = productService.selectById(pid);

		List<ProductImage> productSingleImages = productImageService.selectAll(p, ProductImageDao.type_single);
		List<ProductImage> productDetailImages = productImageService.selectAll(p, ProductImageDao.type_detail);
		p.setProductSingleImages(productSingleImages);
		p.setProductDetailImages(productDetailImages);

		List<PropertyValue> pvs = propertyValueService.selectByProduct(p.getId());

		List<Review> reviews = reviewService.selectAll(p.getId());

		productService.setSaleAndReviewNumber(p);

		request.setAttribute("reviews", reviews);

		request.setAttribute("p", p);
		request.setAttribute("pvs", pvs);
		return "product.jsp";
	}

	public String logout(HttpServletRequest request, HttpServletResponse response, Page page) {
		request.getSession().removeAttribute("user");
		return "@forehome";
	}

	public String checkLogin(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user = (User) request.getSession().getAttribute("user");
		if (null != user)
			return "%success";
		return "%fail";
	}

	public String loginAjax(HttpServletRequest request, HttpServletResponse response, Page page) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = userService.login(name, password);

		if (null == user) {
			return "%fail";
		}
		request.getSession().setAttribute("user", user);
		return "%success";
	}

	public String category(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));

		Category c = new CategoryServiceImpl().selectById(cid);
		new ProductServiceImpl().fill(c);
		new ProductServiceImpl().setSaleAndReviewNumber(c.getProducts());

		String sort = request.getParameter("sort");
		if (null != sort) {
			switch (sort) {
			case "review":
				Collections.sort(c.getProducts(), new ProductReviewComparator());
				break;
			case "date":
				Collections.sort(c.getProducts(), new ProductDateComparator());
				break;

			case "saleCount":
				Collections.sort(c.getProducts(), new ProductSaleCountComparator());
				break;

			case "price":
				Collections.sort(c.getProducts(), new ProductPriceComparator());
				break;

			case "all":
				Collections.sort(c.getProducts(), new ProductAllComparator());
				break;
			}
		}

		request.setAttribute("c", c);
		return "category.jsp";
	}

	public String search(HttpServletRequest request, HttpServletResponse response, Page page) {
		String keyword = request.getParameter("keyword");
		List<Product> ps = new ProductServiceImpl().search(keyword, 0, 20);
		productService.setSaleAndReviewNumber(ps);
		request.setAttribute("ps", ps);
		return "searchResult.jsp";
	}

	public String buyone(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		int num = Integer.parseInt(request.getParameter("num"));
		Product p = productService.selectById(pid);
		int oiid = 0;

		User user = (User) request.getSession().getAttribute("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.selectByUser(user.getId());
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == p.getId()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				oiid = oi.getId();
				break;
			}
		}

		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUser(user);
			oi.setNumber(num);
			oi.setProduct(p);
			orderItemService.add(oi);
			oiid = oi.getId();
		}
		return "@forebuy?oiid=" + oiid;
	}

	public String buy(HttpServletRequest request, HttpServletResponse response, Page page) {
		String[] oiids = request.getParameterValues("oiid");
		List<OrderItem> ois = new ArrayList<>();
		float total = 0;

		for (String strid : oiids) {
			int oiid = Integer.parseInt(strid);
			OrderItem oi = orderItemService.selectById(oiid);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
			ois.add(oi);
		}

		request.getSession().setAttribute("ois", ois);
		request.setAttribute("total", total);
		return "buy.jsp";
	}

	public String addCart(HttpServletRequest request, HttpServletResponse response, Page page) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		Product p = productService.selectById(pid);
		int num = Integer.parseInt(request.getParameter("num"));

		User user = (User) request.getSession().getAttribute("user");
		boolean found = false;

		List<OrderItem> ois = orderItemService.selectByUser(user.getId());
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == p.getId()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				break;
			}
		}

		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUser(user);
			oi.setNumber(num);
			oi.setProduct(p);
			orderItemService.add(oi);
		}
		return "%success";
	}

	public String cart(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user = (User) request.getSession().getAttribute("user");
		List<OrderItem> ois = orderItemService.selectByUser(user.getId());
		request.setAttribute("ois", ois);
		return "cart.jsp";
	}

	public String changeOrderItem(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user = (User) request.getSession().getAttribute("user");
		if (null == user)
			return "%fail";

		int pid = Integer.parseInt(request.getParameter("pid"));
		int number = Integer.parseInt(request.getParameter("number"));
		List<OrderItem> ois = orderItemService.selectByUser(user.getId());
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == pid) {
				oi.setNumber(number);
				orderItemService.update(oi);
				break;
			}

		}
		return "%success";
	}

	public String deleteOrderItem(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user = (User) request.getSession().getAttribute("user");
		if (null == user)
			return "%fail";
		int oiid = Integer.parseInt(request.getParameter("oiid"));
		orderItemService.delete(oiid);
		return "%success";
	}

	public String createOrder(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user = (User) request.getSession().getAttribute("user");

		String address = request.getParameter("address");
		String post = request.getParameter("post");
		String receiver = request.getParameter("receiver");
		String mobile = request.getParameter("mobile");
		String userMessage = request.getParameter("userMessage");

		String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setAddress(address);
		order.setPost(post);
		order.setReceiver(receiver);
		order.setMobile(mobile);
		order.setUserMessage(userMessage);
		order.setCreateDate(new Date());
		order.setUser(user);
		order.setStatus(OrderDao.waitPay);

		orderService.add(order);

		List<OrderItem> ois = (List<OrderItem>) request.getSession().getAttribute("ois");
		float total = 0;
		for (OrderItem oi : ois) {
			oi.setOrder(order);
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
		}

		return "@forealipay?oid=" + order.getId() + "&total=" + total;
	}

	public String alipay(HttpServletRequest request, HttpServletResponse response, Page page) {
		return "alipay.jsp";
	}

	public String payed(HttpServletRequest request, HttpServletResponse response, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order order = orderService.selectById(oid);
		order.setStatus(OrderDao.waitDelivery);
		order.setPayDate(new Date());
		new OrderServiceImpl().update(order);
		request.setAttribute("o", order);
		return "payed.jsp";
	}

	public String bought(HttpServletRequest request, HttpServletResponse response, Page page) {
		User user = (User) request.getSession().getAttribute("user");
		List<Order> os = orderService.selectByUser(user.getId(), OrderDao.delete);

		orderItemService.fill(os);

		request.setAttribute("os", os);

		return "bought.jsp";
	}

	public String confirmPay(HttpServletRequest request, HttpServletResponse response, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderService.selectById(oid);
		orderItemService.fill(o);
		request.setAttribute("o", o);
		return "confirmPay.jsp";
	}

	public String orderConfirmed(HttpServletRequest request, HttpServletResponse response, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderService.selectById(oid);
		o.setStatus(OrderDao.waitReview);
		o.setConfirmDate(new Date());
		orderService.update(o);
		return "orderConfirmed.jsp";
	}

	public String deleteOrder(HttpServletRequest request, HttpServletResponse response, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderService.selectById(oid);
		o.setStatus(OrderDao.delete);
		orderService.update(o);
		return "%success";
	}

	public String review(HttpServletRequest request, HttpServletResponse response, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderService.selectById(oid);
		orderItemService.fill(o);
		Product p = o.getOrderItems().get(0).getProduct();
		List<Review> reviews = reviewService.selectAll(p.getId());
		productService.setSaleAndReviewNumber(p);
		request.setAttribute("p", p);
		request.setAttribute("o", o);
		request.setAttribute("reviews", reviews);
		return "review.jsp";
	}

	public String doreview(HttpServletRequest request, HttpServletResponse response, Page page) {
		int oid = Integer.parseInt(request.getParameter("oid"));
		Order o = orderService.selectById(oid);
		o.setStatus(OrderDao.finish);
		orderService.update(o);
		int pid = Integer.parseInt(request.getParameter("pid"));
		Product p = productService.selectById(pid);

		String content = request.getParameter("content");

		content = HtmlUtils.htmlEscape(content);

		User user = (User) request.getSession().getAttribute("user");
		Review review = new Review();
		review.setContent(content);
		review.setProduct(p);
		review.setCreateDate(new Date());
		review.setUser(user);
		reviewService.add(review);

		return "@forereview?oid=" + oid + "&showonly=true";
	}

}
