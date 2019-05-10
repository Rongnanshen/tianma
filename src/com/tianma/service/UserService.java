package com.tianma.service;

import java.util.List;

import com.tianma.pojo.User;

public interface UserService {

	/*
	 * 获取用户总数
	 */
	int getTotal();

	/*
	 * 增加
	 */
	void add(User user);

	/*
	 * 修改
	 */
	void update(User user);

	/*
	 * 删除
	 */
	void delete(int id);

	/*
	 * 通过id查询
	 */
	User selectById(int id);

	/*
	 * 查询所有（调用分页查询所有方法）
	 */
	List<User> selectAll();

	/*
	 * 分页查询所有
	 */
	List<User> selectAll(int start, int count);

	/*
	 * 判断账号是否已经存在
	 */
	boolean isExistMobile(String mobile);
	
	/*
	 * 判断昵称是否已经存在
	 */
	boolean isExistName(String name);

	/*
	 * 通过手机号查询
	 */
	User selectByMobile(String mobile);
	
	/*
	 * 通过昵称查询
	 */
	User selectByName(String name);

	/*
	 * 登录验证
	 */
	User login(String mobile, String password);

}