package org.ricone.api.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.cache.AppCache;
import org.ricone.api.cache.ProfileCache;
import org.ricone.api.component.config.model.App;
import org.ricone.api.config.ConfigProperties;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.exception.UnauthorizedException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthHandler extends HandlerInterceptorAdapter 
{
	private final String PROPERTY_ALLOW_TOKEN_PARAMETER = "security.auth.allowTokenParameter";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{	
		boolean allowParam = allowTokenParams();
		boolean header = StringUtils.isNotBlank(request.getHeader("Authorization"));
		boolean param = StringUtils.isNotBlank(request.getParameter("access_token"));		
		if(header || param)
		{		
			String token = null;
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
			DecodedToken decodedToken = TokenDecoder.decodeToken(token);
			verified = JWTVerifier.verify(decodedToken);
					
			if(!verified)
			{
				throw new UnauthorizedException("Invalid Token" );
			}
			else if(verified)
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
					App app = AppCache.getInstance().getApp(decodedToken.getApplication_id());
					session.setApp(app);
					session.setProfile(ProfileCache.getInstance().getProfile(app.getProfile_id()));
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
		return BooleanUtils.toBoolean(ConfigProperties.getInstance().getProperty(PROPERTY_ALLOW_TOKEN_PARAMETER));		
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
			else
			{
				throw new UnauthorizedException("Token Expired");
			}
		}
	}
}