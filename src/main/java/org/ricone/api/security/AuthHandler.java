package org.ricone.api.security;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.exception.UnauthorizedException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthHandler extends HandlerInterceptorAdapter 
{
	private final String PROPERTY_ALLOW_TOKEN_PARAMETER = "security.auth.allowTokenParameter";
	private final String PROPERTY_FILE = "config.properties";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{	

		boolean allowParam = allowTokenParams();
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
			else if(param) //Parameter tokens are allowed, and parameter is set
			{
				if(!allowParam) throw new UnauthorizedException("Token Parameter Not Allowed" );
				else token = request.getParameter("access_token");
			}
			verified = JWTVerifier.verify(token);
					
			if(!verified)
			{
				throw new UnauthorizedException("Invalid Token" );
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
			throw new UnauthorizedException("No Token Provided");
		}
		return super.preHandle(request, response, handler);
	}
	
	private boolean allowTokenParams() throws ConfigException
	{
		Resource resource = new ClassPathResource(PROPERTY_FILE);
		Properties props = null;
		try 
		{
			props = PropertiesLoaderUtils.loadProperties(resource);
			return BooleanUtils.toBoolean(props.getProperty(PROPERTY_ALLOW_TOKEN_PARAMETER));
		}
		catch (IOException e) 
		{
			throw new ConfigException("Could not load: " + PROPERTY_FILE);
		}
		
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