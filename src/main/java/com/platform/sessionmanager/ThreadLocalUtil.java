package com.platform.sessionmanager;

import org.springframework.stereotype.Component;

/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午10:07:09
 */


@Component
public class ThreadLocalUtil {
	private static final ThreadLocal<String> UUID = new ThreadLocal<String>();
	/**
	 * 从本地环境变量中获取值
	 * @return
	 */
	public static String get(){
		return UUID.get();
	}
	
	/**
	 * 向本地环境变量中设置值
	 * @param user
	 */
	
	public static void set(String value){
		UUID.set(value);
	}
}
