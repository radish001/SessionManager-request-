package com.platform.sessionmanager;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description
 * @author      胡晓东
 * @date        2017年5月24日下午3:37:07
 */
public interface SessionHandle {

	public void setAttribute(String name, String value) throws SessionException;

	public String getAttribute(String name) throws SessionException;

	public void removeAttribute(String name) throws SessionException;

	public Enumeration<?> getAttributeNames() throws SessionException ;
	
	public void invalidate() throws SessionException;

	public String getId();
}
