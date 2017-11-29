package org.ricone.authentication;

import org.apache.commons.lang3.StringUtils;
import org.ricone.config.cache.AppCache;
import org.ricone.config.model.App;
import org.ricone.config.model.PathPermission;
import org.ricone.error.exception.ForbiddenException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class PermissionHandler extends HandlerInterceptorAdapter
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		System.out.println("PermissionHandler - preHandle");
		System.out.println(StringUtils.strip("/requests/xLeas/*", "/**"));

		//Get AppId from HttpResponse Header passed along by AuthHandler
		String appId = response.getHeader("AppId");

		//Does App have permission to access this page
		Optional<PathPermission> permission = getPathPermission(appId, request.getServletPath());
		if(permission.isPresent() && hasAccess(permission.get(), request.getMethod())) {
			return super.preHandle(request, response, handler);
		}
		else {
			throw new ForbiddenException("Insufficient permissions");
		}
	}

	private Optional<PathPermission> getPathPermission(String appId, String servletPath) {
		App app = AppCache.getInstance().get(appId);
		Optional<PathPermission> pathPermission = app.getPermissions().stream().filter(doesPathMatch(servletPath)).findFirst();
		if(!pathPermission.isPresent()) {
			pathPermission = app.getPermissions().stream().filter(doesPathMatchAny(servletPath)).findFirst();
		}
		return pathPermission;
	}

	private Predicate<PathPermission> doesPathMatch(String servletPath) {
		//Is Path Exact Match - will take precedence over paths like: /requests/xLeas/**
		return p -> p.getPath().equalsIgnoreCase(servletPath);
	}

	private Predicate<PathPermission> doesPathMatchAny(String servletPath) {
		//Does requestPath startWith
		return p -> servletPath.startsWith(p.getPath().replace( "/**", ""));
	}

	private boolean hasAccess(PathPermission permission, String method) {
		System.out.println(method.toUpperCase());
		switch (method.toUpperCase()) {
			case "GET": return permission.getGet();
			case "POST": return permission.getPost();
			case "PUT": return permission.getPut();
			case "DELETE": return permission.getDelete();
			case "HEAD": return permission.getHead();
			default: return false;
		}
	}
}