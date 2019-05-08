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
	 * 将相关的数据库配置信息生成到properties文件中
	 */
	public void fileOutput(String ip,int port,String database,String encoding) {
		
		Properties pro = new Properties();
		
		pro.setProperty("driver", "com.mysql.jdbc.Driver");
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
		pro.setProperty("url",url);
		String user = "root";
		String password = "root";
		
		//调用该方法，生成一对用户名的公钥和私钥
		RSAUtils.RSA();
		userPubKey = RSAUtils.getPubKey();
		userPriKey = RSAUtils.getPriKey();
		
		//System.out.println("GenerateProperties用户名公钥"+userPubKey);
		//System.out.println("GenerateProperties用户名私钥"+userPriKey);
		
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
		
		String encPassword = null;
		try {
			encPassword = RSAUtils.encryptByPublicKey(password, passwordPubKey);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
