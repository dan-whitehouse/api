package org.ricone.api.config.handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ricone.api.exception.UnauthorizedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthHandler extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		if(request.getHeader("Authorization") == null)
		{
			System.out.println("Where my token!");
			throw new UnauthorizedException("No token!");
		}
		
		
		if("true".equals(request.getParameter("clearCache"))) 
		{
			System.out.println("hidden param... :P");
		}
		return super.preHandle(request, response, handler);
	}
}