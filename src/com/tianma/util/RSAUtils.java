package com.tianma.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.crypto.Cipher;
/**
 * 使用RSA算法对用户密码进行加密。具体的步骤如下：
 * 1. 客户端向服务器申请密钥；
 * 2. 服务器接收到客户端的申请以后，生成一对密钥，将公钥发给客户端，私钥自己保存；
 * 3. 客户端接收到公钥以后，使用公钥对密码加密，然后将密文发给服务器；
 * 4. 服务器接收到密文以后，使用私钥解密，判断是否是正确的密码。
 * @author 1599193
 *
 */
public class RSAUtils {

	
	public static void main(String[] args) throws Exception {  
          
        HashMap<String, Object> map = RSAUtils.getKeys();  
        
        //生成公钥和私钥  
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
          
        //模  
        String modulus = publicKey.getModulus().toString();  
        
        //公钥指数  
        String public_exponent = publicKey.getPublicExponent().toString();  
        //私钥指数  
        String private_exponent = privateKey.getPrivateExponent().toString();  
        
        //明文  
        String ming = "123456789";  
        
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);  
        
        //加密后的密文  
        String mi = RSAUtils.encryptByPublicKey(ming, pubKey);  
        System.out.println(mi);  
       
        //解密后的明文  
        ming = RSAUtils.decryptByPrivateKey(mi, priKey);  
        System.out.println(ming);  
    }
	
	/*
	 * 生成公钥和私钥
	 */
	public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {  
        
		HashMap<String, Object> map = new HashMap<String, Object>();  
        
		//实例化KeyPairGenerator对象,指定RSA算法
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        
		//(密钥长度为1024的RSA算法还没有被破解，所以可以认为密钥长度为1024的RSA算法是比较安全的。)
		
		//初始化KeyPairGenerator对象
		keyPairGen.initialize(1024);  
		
		//生成KeyPair对象(KeyPair：包含公钥和私钥的类。)
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        
        //PublicKey和PrivateKey：非对称密钥，即公钥和私钥，DH、RSA、DSA、EC等
        //生成公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
        //生成私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        
        //将公钥和私钥放入到map集合中
        map.put("public", publicKey);  
        map.put("private", privateKey);  
        
        return map;  
    }
	
	/* 
     * 使用模和指数生成RSA公钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA 
     * /None/NoPadding】 
     *  
     * @param modulus 
     *            模 
     * @param exponent 
     *            指数 
     * @return 
     */ 
	public static RSAPublicKey getPublicKey(String modulus, String exponent) {  
        try {  
        	//模
            BigInteger b1 = new BigInteger(modulus);  
            //指数
            BigInteger b2 = new BigInteger(exponent);  
            
            //实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);  
            
            return (RSAPublicKey) keyFactory.generatePublic(keySpec); 
            
        } catch (Exception e) {  
            
        	e.printStackTrace();  
            return null;  
        }  
    }
	
	/* 
     * 使用模和指数生成RSA私钥 
     * 注意：【此代码用了默认补位方式，为RSA/None/PKCS1Padding，不同JDK默认的补位方式可能不同，如Android默认是RSA 
     * /None/NoPadding】 
     *  
     * @param modulus 
     *            模 
     * @param exponent 
     *            指数 
     * @return 
     */
	public static RSAPrivateKey getPrivateKey(String modulus, String exponent) {  
        
		try {  
			//模
            BigInteger b1 = new BigInteger(modulus); 
            //指数
            BigInteger b2 = new BigInteger(exponent); 
            
            //实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);  
            
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            
        } catch (Exception e) {  
            
        	e.printStackTrace();  
            return null;  
        }  
    } 
	
	/*
	 * 公钥加密
	 */
	public static String encryptByPublicKey(String data, RSAPublicKey publicKey) throws Exception {  
        
		//实例化
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");  
        
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        
		// 模长  
        int key_len = publicKey.getModulus().bitLength() / 8;  
        
        // 加密数据长度 <= 模长-11  
        String[] datas = splitString(data, key_len - 11);  
        
        String mi = "";  
        
        //如果明文长度大于模长-11则要分组加密  
        for (String s : datas) {  
            mi += bcd2Str(cipher.doFinal(s.getBytes()));  
        }  
        return mi;  
    } 
	
	/*
	 * 私钥解密
	 */
	public static String decryptByPrivateKey(String data, RSAPrivateKey privateKey) throws Exception {  
        
		Cipher cipher = Cipher.getInstance("RSA");  
        
		cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        
		//模长  
        int key_len = privateKey.getModulus().bitLength()/8;  
        
        byte[] bytes = data.getBytes();  
        byte[] bcd = ASCII_To_BCD(bytes, bytes.length);  
        
        System.err.println(bcd.length);  
        
        //如果密文长度大于模长则要分组解密  
        String ming = "";  
        
        byte[][] arrays = splitArray(bcd, key_len);  
        
        for(byte[] arr : arrays){  
            ming += new String(cipher.doFinal(arr));  
        }  
        return ming;  
    }
	
	/*
	 * ASCII码转BCD码 
	 */
	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {  
        
		byte[] bcd = new byte[asc_len / 2];  
        
		int j = 0;  
        
		for (int i = 0; i < (asc_len + 1) / 2; i++) {  
            bcd[i] = asc_to_bcd(ascii[j++]);  
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));  
        }  
        return bcd;  
    }
	
	/*
	 * ASCII码转BCD码 
	 */
	public static byte asc_to_bcd(byte asc) {  
        
		byte bcd;  
  
        if ((asc >= '0') && (asc <= '9'))  
            bcd = (byte) (asc - '0');  
        else if ((asc >= 'A') && (asc <= 'F'))  
            bcd = (byte) (asc - 'A' + 10);  
        else if ((asc >= 'a') && (asc <= 'f'))  
            bcd = (byte) (asc - 'a' + 10);  
        else  
            bcd = (byte) (asc - 48);  
        return bcd;  
    }
	
	/*
	 * BCD转字符串
	 */
	public static String bcd2Str(byte[] bytes) {  
        
		char temp[] = new char[bytes.length * 2], val;  
  
        for (int i = 0; i < bytes.length; i++) {  
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);  
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
  
            val = (char) (bytes[i] & 0x0f);  
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');  
        }  
        return new String(temp);  
    }
	
	/*
	 * 拆分字符串
	 */
	public static String[] splitString(String string, int len) {  
        
		int x = string.length() / len;  
        int y = string.length() % len;  
        int z = 0;  
        
        if (y != 0) {  
            z = 1;  
        }  
        
        String[] strings = new String[x + z];  
        String str = "";  
        
        for (int i=0; i<x+z; i++) {  
            if (i==x+z-1 && y!=0) {  
                str = string.substring(i*len, i*len+y);  
            }else{  
                str = string.substring(i*len, i*len+len);  
            }  
            strings[i] = str;  
        }  
        
        return strings;  
    }
	
	/*
	 * 拆分数组
	 */
	public static byte[][] splitArray(byte[] data,int len){  
        
		int x = data.length / len;  
        int y = data.length % len;  
        int z = 0;  
        
        if(y!=0){  
            z = 1;  
        }  
        
        byte[][] arrays = new byte[x+z][];  
        byte[] arr;  
        
        for(int i=0; i<x+z; i++){  
            arr = new byte[len];  
            if(i==x+z-1 && y!=0){  
                System.arraycopy(data, i*len, arr, 0, y);  
            }else{  
                System.arraycopy(data, i*len, arr, 0, len);  
            }  
            arrays[i] = arr;  
        }  
       
        return arrays;  
    }
}
