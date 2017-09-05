package org.ricone.api.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.cache.AppCache;
import org.ricone.api.cache.ProfileCache;
import org.ricone.api.component.config.model.App;
import org.ricone.api.exception.UnauthorizedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Date;

public class AuthHandler extends HandlerInterceptorAdapter 
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		AuthRequest authRequest = new AuthRequest(request);

		if(isPathException(request.getServletPath()))
		{
			return super.preHandle(request, response, handler);
		}
		else if(authRequest.isHeader() || (authRequest.isParameter() && authRequest.isAllowTokenParameter()))
		{
			DecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken());
			if(JWTVerifier.verify(decodedToken))
			{
				Session session = SessionManager.getInstance().getSessions().get(decodedToken.getApplication_id());
				if(session != null)
				{
					checkAgainstExisting(decodedToken, session);
				}
				else
				{
					session = new Session();
					session.setToken(decodedToken);
					App app = AppCache.getInstance().get(decodedToken.getApplication_id());
					session.setApp(app);
					session.setProfile(ProfileCache.getInstance().get(app.getProfile_id()));
					SessionManager.getInstance().addSession(decodedToken.getApplication_id(), session);
				}
				return super.preHandle(request, response, handler);
			}
			else
			{
				Date now = new Date();
				if(decodedToken.getExp().before(now))
				{
					throw new UnauthorizedException("Token Expired");
				}
				else
				{
					throw new UnauthorizedException("Invalid Token");
				}
			}
		}
		else if(authRequest.isParameter() && !authRequest.isAllowTokenParameter())
		{
			throw new UnauthorizedException("Token Parameter Not Allowed");
		}
		else
		{
			throw new UnauthorizedException("No Token Provided");
		}
	}

	//This method checks to see if the servletPath being requested is an exception to the rule of needing a token
	private boolean isPathException(String servletPath)
	{
		System.out.println(servletPath);
		if("/swagger/api-docs".equalsIgnoreCase(servletPath))
		{
			return true;
		}
		else if("/info".equalsIgnoreCase(servletPath))
		{
			return true;
		}
		else if("/".equals(servletPath))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void checkAgainstExisting(DecodedToken decodedToken, Session session) throws UnauthorizedException 
	{
		if(!StringUtils.equalsIgnoreCase(decodedToken.getTokenString(), session.getToken().getTokenString()))
		{	
			boolean isNewer = decodedToken.getExp().after(session.getToken().getExp());		
			if(isNewer)
			{
				session.setToken(decodedToken);
			}
		}
	}
}