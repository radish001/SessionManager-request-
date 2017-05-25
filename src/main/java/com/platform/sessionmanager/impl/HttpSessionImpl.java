package com.platform.sessionmanager.impl;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.platform.sessionmanager.SessionException;
import com.platform.sessionmanager.SessionHandle;

/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日上午10:02:51
 */


//@Service("sessionHandle")
public class HttpSessionImpl implements SessionHandle{

	
	@Autowired
	private HttpServletRequest request;
	
	public String getId(){
		return request.getSession().getId();
	}

	public void setAttribute(String name, String value) throws SessionException {
		request.getSession().setAttribute(name, value);
	}


	public String getAttribute(String name) throws SessionException {
		return (String)request.getSession().getAttribute(name);
	}

	public void removeAttribute(String name) throws SessionException {
		request.getSession().removeAttribute(name);
	}

	public Enumeration<?> getAttributeNames() throws SessionException {
		return request.getSession().getAttributeNames();
	}
	
	public void invalidate() throws SessionException {
		request.getSession().invalidate();
	}


}
