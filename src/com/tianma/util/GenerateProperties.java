package com.tianma.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Properties;

public class GenerateProperties {
	 
	private static RSAPublicKey userPubKey;
	private static RSAPrivateKey userPriKey;
	private static RSAPublicKey passwordPubKey;
	private static RSAPrivateKey passwordPriKey;
	
	
	public RSAPublicKey getUserPubKey() {
		return userPubKey;
	}

	public RSAPrivateKey getUserPriKey() {
		return userPriKey;
	}

	public RSAPublicKey getPasswordPubKey() {
		return passwordPubKey;
	}

	public RSAPrivateKey getPasswordPriKey() {
		return passwordPriKey;
	}

	/*
	 * 根据用户数据库相关连接信息生成db.properties文件，用于存储数据库连接信息
	 * fileOutput()方法共7个参数，用户根据自己数据库信息传参，从而生成db.properties文件
	 * 7个参数按顺序分别为：
	 * ip地址、端口号、数据库名称、编码方式、连接驱动、用户名、密码
	 */
    public static void main(String []args) {
    	fileOutput("127.0.0.1",3306,"tianma","UTF-8","com.mysql.jdbc.Driver","root","root");
    }

	/*
	 * 将相关的数据库配置信息生成到properties文件中
	 */
	public static void fileOutput(String ip,int port,String database,String encoding,String driver,String user,String password) {
		
		//调用该方法，生成一对用户名的公钥和私钥
		RSAUtils.RSA();
		userPubKey = RSAUtils.getPubKey();
		userPriKey = RSAUtils.getPriKey();
		
		//System.out.println("GenerateProperties用户名公钥"+userPubKey);
		//System.out.println("GenerateProperties用户名私钥"+userPriKey);
		
		//对用户名加密
		String encUser = null;
		try {
			encUser = RSAUtils.encryptByPublicKey(user, userPubKey);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//调用该方法，生成一对密码的公钥和私钥
		RSAUtils.RSA();
		passwordPubKey = RSAUtils.getPubKey();
		passwordPriKey = RSAUtils.getPriKey();
		
		//System.out.println("GenerateProperties密码公钥"+passwordPubKey);
		//System.out.println("GenerateProperties密码私钥"+passwordPriKey);
		
		//对密码加密
		String encPassword = null;
		try {
			encPassword = RSAUtils.encryptByPublicKey(password, passwordPubKey);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        Properties pro = new Properties();
		
		pro.setProperty("driver", driver);
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
		pro.setProperty("url",url);
		pro.setProperty("user", encUser);
		pro.setProperty("password", encPassword);
		
		//创建输出流对象
		FileOutputStream fos = null;
		FileWriter fw = null;
		File f = new File("db.properties");
		
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fw =new FileWriter(f);
            //清空文件内容
			fw.write("");
			fw.flush();
			fos = new FileOutputStream(f);
			pro.store(fos, "db configure!");
			System.out.println("生成db.properties文件成功!");
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fw!=null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
