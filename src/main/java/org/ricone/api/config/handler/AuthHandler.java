package org.ricone.api.config.handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.exception.UnauthorizedException;
import org.ricone.api.security.JWTVerifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthHandler extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		if(StringUtils.isBlank(request.getHeader("Authorization")))
		{
			throw new UnauthorizedException("No token!");
		}
		else if(StringUtils.isNotBlank(request.getHeader("Authorization")))
		{
			JWTVerifier verifier = new JWTVerifier();
			boolean verified = verifier.verify(request.getHeader("Authorization"));
			
			if(!verified)
			{
				throw new UnauthorizedException("Validation with security service failed.");
			}
		}
		
		if("true".equals(request.getParameter("clearCache"))) 
		{
			System.out.println("hidden param... :P");
		}
		return super.preHandle(request, response, handler);
	}
}