package org.ricone.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogHandler extends HandlerInterceptorAdapter 
{	
	private static final Logger logger = LogManager.getLogger(LogHandler.class);
	 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{	
		log(request, response);
		return super.preHandle(request, response, handler);
	}
	
	private void log(HttpServletRequest request, HttpServletResponse response)
	{
		Log log = new Log(request, response);
		logger.log(log.getLevel(), log.toString());
	}
}