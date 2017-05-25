package com.platform.sessionmanager;
/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午10:04:06
 */
public class SessionException extends Exception{
    public SessionException() {
		
	}
	
	public SessionException(String msg){
		super(msg);
	}
	
    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
