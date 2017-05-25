package com.platform.sessionmanager;


import org.springframework.stereotype.Service;

/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午9:53:58
 */
@Service("sessionConfig")
public class SessionConfig {
	public  final String DEFAULT_SESSIONTYPE = "http";
	
	private String sessionType = "redis";
	private int redisSessionValid=60*15;
	private String redisIp="192.168.59.129";
    private int redisPort=6379;
    private int redisMinIdle=300;
    private int redisMaxIdle=500;
    private int redisMaxTotal=5000;
    private String redisCookieName="Test";
    private int redisCookieValid=-1;
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	public int getRedisSessionValid() {
		return redisSessionValid;
	}
	public void setRedisSessionValid(int redisSessionValid) {
		this.redisSessionValid = redisSessionValid;
	}
	public String getRedisIp() {
		return redisIp;
	}
	public void setRedisIp(String redisIp) {
		this.redisIp = redisIp;
	}
	public int getRedisPort() {
		return redisPort;
	}
	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}
	public int getRedisMinIdle() {
		return redisMinIdle;
	}
	public void setRedisMinIdle(int redisMinIdle) {
		this.redisMinIdle = redisMinIdle;
	}
	public int getRedisMaxIdle() {
		return redisMaxIdle;
	}
	public void setRedisMaxIdle(int redisMaxIdle) {
		this.redisMaxIdle = redisMaxIdle;
	}
	public int getRedisMaxTotal() {
		return redisMaxTotal;
	}
	public void setRedisMaxTotal(int redisMaxTotal) {
		this.redisMaxTotal = redisMaxTotal;
	}
	public String getRedisCookieName() {
		return redisCookieName;
	}
	public void setRedisCookieName(String redisCookieName) {
		this.redisCookieName = redisCookieName;
	}
	public int getRedisCookieValid() {
		return redisCookieValid;
	}
	public void setRedisCookieValid(int redisCookieValid) {
		this.redisCookieValid = redisCookieValid;
	}
	public  String getDefaultSessiontype() {
		return DEFAULT_SESSIONTYPE;
	}
    
	
    
  
    
	

    
}
