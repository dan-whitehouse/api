package org.ricone.authentication;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.util.Util;
import org.ricone.api.xPress.request.xLea.ILeaService;
import org.ricone.authentication.session.Session;
import org.ricone.authentication.session.SessionManager;
import org.ricone.config.AppCache;
import org.ricone.config.model.App;
import org.ricone.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class AuthHandler extends HandlerInterceptorAdapter 
{
	@Autowired
	private ILeaService leaService;

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
					//If app existing in cache already, use cache, otherwise pull from config service
					App app = AppCache.getInstance().get(decodedToken.getApplication_id());

					/*
					*  Use App, Token, and Request to create MetaData needed to make a request to collect the Lea's.
					*  I wanted to handle this in the AppCache, but due to limited request information I need to do it on the first authorization of the User
					*  If the users first request is to xLeas, the leaService is technically being called twice.
					*  Which is ideal, because the request is to an object with no complex joins.
					*/
					MetaData metaData = new MetaData();
					metaData.setApp(app);
					metaData.setPaging(Pageable.unpaged());
					metaData.setHeaders(Util.getHeaders(request));
					metaData.setToken(decodedToken);

					app.setLeas(leaService.findAll(metaData));

					session = new Session();
					session.setToken(decodedToken);
					session.setApp(app);
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