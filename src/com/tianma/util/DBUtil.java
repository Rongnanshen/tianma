package com.tianma.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static String ip = "127.0.0.1";
	private static int port = 3306;
	private static String database = "tianma";
	private static String encoding = "UTF-8";
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	static {
		try {
			//将相关的数据库配置信息生成到properties文件中
			GenerateProperties generateProperties = new GenerateProperties();
			generateProperties.fileOutput(ip, port, database, encoding);
			
			// 用流读入properties配置文件
			InputStream inputStream = new BufferedInputStream(new FileInputStream(new File("db.properties")));
			Properties properties = new Properties();
			// 从输入字节流读取属性列表（键和元素对）
			properties.load(inputStream);
			// 用此属性列表中指定的键搜索属性，获取驱动，url，username，password
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			
			//System.out.println("用户名私钥："+generateProperties.getUserPriKey());
			try {
				user = RSAUtils.decryptByPrivateKey(properties.getProperty("user"), generateProperties.getUserPriKey())
						.trim();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				password = RSAUtils
						.decryptByPrivateKey(properties.getProperty("password"), generateProperties.getPasswordPriKey())
						.trim();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(driver);
			System.out.println(url);
			System.out.println(user);
			System.out.println(password);
			// 加载驱动
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) throws SQLException {

		System.out.println(getConnection());

	}

}
