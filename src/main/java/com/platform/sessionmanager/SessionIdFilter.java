package com.platform.sessionmanager;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.platform.sessionmanager.SessionConfig;


/**
 * @description
 * @author 胡晓东
 * @date 2017年5月24日上午10:04:52
 */
public class SessionIdFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(SessionIdFilter.class);
	private ApplicationContext applicationContext;
	private SessionConfig sessionConfig;

	@Override
	public void destroy() {


	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		sessionConfig = (SessionConfig) applicationContext.getBean("sessionConfig");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!sessionConfig.DEFAULT_SESSIONTYPE.equals(sessionConfig.getSessionType())) {
			HttpServletRequest httpServletRequest = (HttpServletRequest)request;
			HttpServletResponse httpServletResponse = (HttpServletResponse)response;
			String sessionId = CookieUtils.getCookieValue(httpServletRequest,sessionConfig.getRedisCookieName());
			if (sessionId == null || sessionId.trim().equals("")) {
				sessionId = UUID.randomUUID().toString(); 
				CookieUtils.setCookie(httpServletRequest, httpServletResponse, sessionConfig.getRedisCookieName(), sessionId, sessionConfig.getRedisCookieValid());
			    LOG.debug("初始化RedisSession成功，sessionId为："+sessionId);
			}
			ThreadLocalUtil.set(sessionId);
			LOG.debug("过滤完毕,sessionID为："+ThreadLocalUtil.get());
		}
		chain.doFilter(request, response);
	}
}
