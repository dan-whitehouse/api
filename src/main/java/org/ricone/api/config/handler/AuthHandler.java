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
		boolean header = StringUtils.isNotBlank(request.getHeader("Authorization"));
		boolean param = StringUtils.isNotBlank(request.getParameter("access_token"));
		if(header || param)
		{
			JWTVerifier verifier = new JWTVerifier();
			boolean verified = false;
			if(header)
			{
				verified = verifier.verify(request.getHeader("Authorization"));
			}
			else if(param)
			{
				verified = verifier.verify(request.getParameter("access_token"));
			}		
			if(!verified)
			{
				throw new UnauthorizedException("Validation with security service failed.");
			}
		}
		else
		{
			throw new UnauthorizedException("Validation with security service failed.");
		}
		return super.preHandle(request, response, handler);
	}
}

//if(StringUtils.isBlank(request.getHeader("Authorization")))
//{
//	throw new UnauthorizedException("No token!");
//}
//else if(StringUtils.isNotBlank(request.getHeader("Authorization")))
//{
//	JWTVerifier verifier = new JWTVerifier();
//	boolean verified = verifier.verify(request.getHeader("Authorization"));
//	
//	if(!verified)
//	{
//		throw new UnauthorizedException("Validation with security service failed.");
//	}
//}
//else if(StringUtils.isBlank(request.getParameter("access_token")))
//{
//	JWTVerifier verifier = new JWTVerifier();
//}