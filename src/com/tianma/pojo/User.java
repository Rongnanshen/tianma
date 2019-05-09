package com.tianma.pojo;

import java.io.Serializable;

public class User implements Serializable {

	//id
	private int id;
	
	//昵称
    private String name;
    
    //手机号（账号）
    private String mobile;
    
    //密码
	private String password;
	
	//角色（用户或管理员）
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
