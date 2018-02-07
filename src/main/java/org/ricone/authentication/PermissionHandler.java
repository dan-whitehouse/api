package org.ricone.authentication;

import org.apache.commons.collections.CollectionUtils;
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
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class PermissionHandler extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PermissionHandler - preHandle");

        //Get AppId from HttpResponse Header passed along by AuthHandler
        String appId = response.getHeader("AppId");

        //Does App have permission to access this page
        Optional<PathPermission> permission = getPathPermission(appId, request.getServletPath(), request.getParameterMap()); //getPathPermission -> doesPathMatch -> isMatch -> removeLastSlash
        if(permission.isPresent() && hasAccess(permission.get(), request.getMethod())) {
            return super.preHandle(request, response, handler);
        }
        throw new ForbiddenException("Insufficient permissions");
    }

    private Optional<PathPermission> getPathPermission(String appId, String servletPath, Map<String, String[]> parameterMap) {
        App app = AppCache.getInstance().get(appId);
        try {
            return app.getPermissions().stream().filter(doesPathMatch(servletPath, parameterMap)).findFirst();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Predicate<PathPermission> doesPathMatch(String servletPath, Map<String, String[]> parameterMap) {
        return p -> isMatch(p.getPath(), servletPath, parameterMap);
    }

    private boolean isMatch(String path, String servletPath, Map<String, String[]> parameterMap) {
        //Clean
        path = removeLastSlash(path);
        servletPath = removeLastSlash(servletPath);

        //Split
        String[] pathArray = StringUtils.split(path, "/");
        String[] servletPathArray = StringUtils.split(servletPath, "/");

        System.err.println(pathArray.length + " | " + servletPathArray.length);

        //Build
        List<String> list = new ArrayList<>();
        if(pathArray.length <= servletPathArray.length) {
            for (int i = 0; i < pathArray.length; i++) {
                if("{}".equals(pathArray[i])) {
                    list.add(servletPathArray[i]);
                }
                else {
                    list.add(pathArray[i]);
                }
            }
        }
        String result = "/" + StringUtils.join(list, "/");

        //System.err.println(result + " | " + servletPath);

        if(CollectionUtils.isNotEmpty(parameterMap.keySet())) {
            Optional<String> feature = parameterMap.keySet().stream().filter(this::isFeature).findFirst();
            if(feature.isPresent()) {
                servletPath = servletPath + "?" + feature.get();
            }
        }
        //System.err.println(result + " | " + servletPath);


        //Compare
        return servletPath.equalsIgnoreCase(result);
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
            case "GET":
                return permission.getGet();
            case "POST":
                return permission.getPost();
            case "PUT":
                return permission.getPut();
            case "DELETE":
                return permission.getDelete();
            case "HEAD":
                return permission.getHead();
            default:
                return false;
        }
    }

    private boolean isFeature(String key) {
        return key.equalsIgnoreCase("changesSinceMarker") || key.equalsIgnoreCase("userPasswords");
    }
}