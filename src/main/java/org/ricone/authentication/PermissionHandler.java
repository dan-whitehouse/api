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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class PermissionHandler extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		System.out.println("PermissionHandler - preHandle");

		//Get AppId from HttpResponse Header passed along by AuthHandler
		String appId = response.getHeader("AppId");

		//Does App have permission to access this page
		Optional<PathPermission> permission = getPathPermission(appId, request.getServletPath()); //getPathPermission -> doesPathMatch -> isMatch -> removeLastSlash
		if(permission.isPresent() && hasAccess(permission.get(), request.getMethod())) {
			return super.preHandle(request, response, handler);
		}
		throw new ForbiddenException("Insufficient permissions");
	}

	private Optional<PathPermission> getPathPermission(String appId, String servletPath) {
		App app = AppCache.getInstance().get(appId);
		return app.getPermissions().stream().filter(doesPathMatch(servletPath)).findFirst();
	}

	private Predicate<PathPermission> doesPathMatch(String servletPath) {
		return p -> isMatch(p.getPath(), servletPath);
	}

	private boolean isMatch(String path, String servletPath) {
		//Clean
		path = removeLastSlash(path);
		servletPath = removeLastSlash(servletPath);

		//Split
		String[] pathArray = StringUtils.split(path, "/");
		String[] servletPathArray = StringUtils.split(servletPath, "/");

		//Build
		List<String> list = new ArrayList<>();
		for(int i = 0; i < pathArray.length; i++) {
			if(pathArray[i] != null && servletPathArray[i] != null) {
				if("{}".equals(pathArray[i])) {
					list.add(servletPathArray[i]);
				}
				else {
					list.add(pathArray[i]);
				}
			}
		}
		String result = "/" + StringUtils.join(list, "/");

		//Compare
		return result.equalsIgnoreCase(servletPath);
	}

	private String removeLastSlash(String url) {
		if(url.endsWith("/")) {
			return url.substring(0, url.lastIndexOf("/"));
		}
		return url;
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