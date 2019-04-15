package com.tianma.service.impl;

import java.util.List;

import com.tianma.dao.UserDao;
import com.tianma.dao.impl.UserDaoImpl;
import com.tianma.pojo.User;
import com.tianma.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = null;
	
	/*
	 * 获取用户总数
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#getTotal()
	 */
	@Override
	public int getTotal() {
		userDao = new UserDaoImpl();
		return userDao.getTotal();
	}

	/*
	 * 增加
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#add(com.tianma.pojo.User)
	 */
	@Override
	public void add(User user) {
		userDao = new UserDaoImpl();
		userDao.add(user);
	}

	/*
	 * 修改
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#update(com.tianma.pojo.User)
	 */
	@Override
	public void update(User user) {
		userDao = new UserDaoImpl();
		userDao.update(user);
	}

	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#delete(int)
	 */
	@Override
	public void delete(int id) {
		userDao = new UserDaoImpl();
		userDao.delete(id);
	}

	/*
	 * 通过id查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#selectById(int)
	 */
	@Override
	public User selectById(int id) {
		userDao = new UserDaoImpl();
		return userDao.selectById(id);
	}

	/*
	 * 查询所有（调用分页查询所有方法）
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#selectAll()
	 */
	@Override
	public List<User> selectAll() {
		userDao = new UserDaoImpl();
		return userDao.selectAll();
	}

	/*
	 * 分页查询所有
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#selectAll(int, int)
	 */
	@Override
	public List<User> selectAll(int start, int count) {
		userDao = new UserDaoImpl();
		return userDao.selectAll(start, count);
	}

	/*
	 * 判断账号是否已经存在
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#isExist(java.lang.String)
	 */
	@Override
	public boolean isExist(String mobile) {
		userDao = new UserDaoImpl();
		return userDao.isExist(mobile);
	}

	/*
	 * 通过手机号查询
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#selectByMobile(java.lang.String)
	 */
	@Override
	public User selectByMobile(String mobile) {
		userDao = new UserDaoImpl();
		return userDao.selectByMobile(mobile);
	}

	/*
	 * 登录验证
	 */
	/* (non-Javadoc)
	 * @see com.tianma.service.impl.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String mobile, String password) {
		userDao = new UserDaoImpl();
		return userDao.login(mobile, password);
	}

	
}
