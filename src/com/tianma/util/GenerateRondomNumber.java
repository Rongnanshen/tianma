package com.tianma.util;

import java.util.Random;

public class GenerateRondomNumber {

	public int getNumber() {
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		//随机生成数字，并添加到字符串
		for(int i=0;i<8;i++){
		    str.append(random.nextInt(10));
		}
		//将字符串转换为数字并输出
		int num=Integer.parseInt(str.toString());
		return num;
	}
	
}
