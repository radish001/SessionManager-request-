package com.platform.sessionmanager;

import java.util.Enumeration;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日下午4:08:04
 */
@Service("sessionManager")
public class SessionManager {
	
	@Resource(name="sessionHandle")
	private SessionHandle sessionHandle;
	
	public String getSessionId(){
		return sessionHandle.getId();
	}

	public void setAttribute(String name, String value) throws SessionException {
		sessionHandle.setAttribute(name, value);
	}

	public String getAttribute(String name) throws SessionException {
		return (String)sessionHandle.getAttribute(name);
	}

	public void removeAttribute(String name) throws SessionException {
		sessionHandle.removeAttribute(name);
	}

	public Enumeration<?> getAttributeNames() throws SessionException {
		return sessionHandle.getAttributeNames();
	}
	
	public void invalidate() throws SessionException {
		sessionHandle.invalidate();
	}
}
