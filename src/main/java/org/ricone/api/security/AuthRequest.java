package org.ricone.api.security;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.config.ConfigProperties;
import org.ricone.api.exception.ConfigException;

import javax.servlet.http.HttpServletRequest;


class AuthRequest
{
    private boolean allowTokenParameter;
    private boolean isParameter;
    private boolean isHeader;
    private String token;

    AuthRequest(HttpServletRequest request) throws ConfigException
    {
        allowTokenParameter = allowTokenParams();
        isHeader = StringUtils.isNotBlank(request.getHeader("Authorization"));
        isParameter = StringUtils.isNotBlank(request.getParameter("access_token"));

        if(isHeader)
        {
            token = request.getHeader("Authorization");
        }
        else if(isParameter) //Parameter tokens are allowed, and parameter is set
        {
            token = request.getParameter("access_token");
        }
    }

    private boolean allowTokenParams() throws ConfigException
    {
        return BooleanUtils.toBoolean(ConfigProperties.getInstance().getProperty("security.auth.allowTokenParameter"));
    }

    boolean isAllowTokenParameter() {
        return allowTokenParameter;
    }

    boolean isParameter() {
        return isParameter;
    }

    boolean isHeader() {
        return isHeader;
    }

    String getToken() {
        return token;
    }
}
