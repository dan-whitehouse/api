package org.ricone.authentication;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.util.Util;
import org.ricone.api.xPress.request.xLea.ILeaService;
import org.ricone.authentication.session.Session;
import org.ricone.authentication.session.SessionManager;
import org.ricone.config.cache.AppCache;
import org.ricone.config.model.App;
import org.ricone.config.model.PathPermission;
import org.ricone.error.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthHandler extends HandlerInterceptorAdapter 
{
	@Autowired
	private ILeaService leaService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		System.out.println("AuthHandler - preHandle ");
		AuthRequest authRequest = new AuthRequest(request);
		if(isPathException(request.getServletPath())) //is request path an exception to our authentication rules
		{
			return super.preHandle(request, response, handler);
		}
		else if(authRequest.isHeader() || (authRequest.isParameter() && authRequest.isAllowTokenParameter())) //is request on the header, or is it a parameter(but only if we allow it)
		{
			DecodedToken decodedToken = TokenDecoder.decodeToken(authRequest.getToken());
			if(JWTVerifier.verify(decodedToken)) {
				Session session = SessionManager.getInstance().getSessions().get(decodedToken.getApplication_id());
				if(session != null) {
					checkAgainstExisting(decodedToken, session);
				}
				else {
					App app = AppCache.getInstance().get(decodedToken.getApplication_id());
					app.getPermissions().addAll(pathPermissions()); //TODO - Connect this to config, and remove this static test

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

					//Add Lea objects to App
					for(LeaWrapper leaWrapper : leaService.findAll(metaData)) {
						app.getLeas().add(leaWrapper.getLea());
					}



					//If no session exists, create a new one
					SessionManager.getInstance().addSession(decodedToken.getApplication_id(), new Session(decodedToken, app));
				}
				//Set HttpResponse Header so we can use it in the PermissionHandler
				response.setHeader("AppId", decodedToken.getApplication_id());
				return super.preHandle(request, response, handler);
			}
			else {
				Date now = new Date();
				if(decodedToken.getExp().before(now)) {
					throw new UnauthorizedException("Token Expired");
				}
				else {
					throw new UnauthorizedException("Invalid Token");
				}
			}
		}
		else if(authRequest.isParameter() && !authRequest.isAllowTokenParameter()) {
			throw new UnauthorizedException("Token Parameter Not Allowed");
		}
		else {
			throw new UnauthorizedException("No Token Provided");
		}
	}

	private List<PathPermission> pathPermissions() {
		PathPermission pathPermission1 = new PathPermission();
		pathPermission1.setPath("/requests/xLeas");
		pathPermission1.setGet(true);

		PathPermission pathPermission2 = new PathPermission();
		pathPermission2.setPath("/requests/xLeas/{}");
		pathPermission2.setGet(true);

		List<PathPermission> pathPermissions = new ArrayList<>();
		pathPermissions.add(pathPermission1);
		pathPermissions.add(pathPermission2);

		return pathPermissions;
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

	private void checkAgainstExisting(DecodedToken decodedToken, Session session) throws UnauthorizedException {
		/* The token has already been verified for this app. If the incoming request token doesn't match our session token and is newer, update our session token.
		   Tokens with an older expiration date are still valid, but we could change this by uncommenting the exception below.
		   Though this would limit applications from running multiple clients simultaneously where they would each generate a new token, expiring the ones generated before.
		 */
		if(!StringUtils.equalsIgnoreCase(decodedToken.getTokenString(), session.getToken().getTokenString())) {
			boolean isNewer = decodedToken.getExp().after(session.getToken().getExp());		
			if(isNewer) {
				session.setToken(decodedToken);
			}
		}
		//throw new UnauthorizedException("Token Provided Is Valid, But A Newer Token Has Been Generated");
	}
}