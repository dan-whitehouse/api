package org.ricone.api.config.handler;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.exception.UnauthorizedException;
import org.ricone.api.security.DecodedToken;
import org.ricone.api.security.JWTVerifier;
import org.ricone.api.security.Session;
import org.ricone.api.security.SessionManager;
import org.ricone.api.security.TokenDecoder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthHandler extends HandlerInterceptorAdapter 
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{	
		boolean header = StringUtils.isNotBlank(request.getHeader("Authorization"));
		boolean param = StringUtils.isNotBlank(request.getParameter("access_token"));
		String token = null;
		if(header || param)
		{			
			boolean verified = false;
			if(header)
			{
				token = request.getHeader("Authorization");				
			}
			else if(param)
			{
				token = request.getParameter("access_token");
			}
			verified = JWTVerifier.verify(token);
					
			if(!verified)
			{
				throw new UnauthorizedException("Validation with security service failed: Invalid Token" );
			}
			else if(verified)
			{
				DecodedToken decodedToken = TokenDecoder.decodeToken(token);
				Session session = SessionManager.getInstance().getSessions().get(decodedToken.getApplication_id());
				if(session != null)
				{
					checkAgainstExisting(decodedToken, session);
				}
				else
				{
					session = new Session();
					session.setToken(decodedToken);
					SessionManager.getInstance().addSession(decodedToken.getApplication_id(), session);
				}
			}
		}
		else
		{
			throw new UnauthorizedException("Validation with security service failed: No Token Provided");
		}
		return super.preHandle(request, response, handler);
	}

	private void checkAgainstExisting(DecodedToken decodedToken, Session session) throws UnauthorizedException 
	{
		if(!StringUtils.equalsIgnoreCase(decodedToken.getTokenString(), session.getToken().getTokenString()))
		{
			Date d1 = new Date(decodedToken.getExp());
			Date d2 = new Date(session.getToken().getExp());	
			boolean isNewer = d1.after(d2);
			
			if(isNewer)
			{
				session.setToken(decodedToken);
			}
			else
			{
				throw new UnauthorizedException("Validation with security service failed: Token Expired" );
			}
		}
	}
}