package org.ricone.authentication;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.error.exception.ConfigException;
import org.ricone.init.ConfigProperties;

import javax.servlet.http.HttpServletRequest;

public class AuthRequest
{
    private boolean allowTokenParameter;
    private boolean isParameter;
    private boolean isHeader;
    private String token;

    public AuthRequest(HttpServletRequest request) throws ConfigException
    {
        allowTokenParameter = allowTokenParams();
        isHeader = StringUtils.isNotBlank(request.getHeader("Authorization"));
        isParameter = StringUtils.isNotBlank(request.getParameter("access_token"));

        if(isHeader)
        {
            //Strip away the key if Bearer, otherwise it will keep it's key and fail
            token = StringUtils.replace(request.getHeader("Authorization"), "Bearer ",  "");
        }
        else if(isParameter) //Parameter tokens are allowed, and parameter is set
        {
            token = request.getParameter("access_token");
        }
    }

    boolean allowTokenParams() throws ConfigException {
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

    public String getToken() {
        return token;
    }


    @Override
    public String toString() {
        return "AuthRequest{" +
                "allowTokenParameter=" + allowTokenParameter +
                ", isParameter=" + isParameter +
                ", isHeader=" + isHeader +
                ", token='" + token + '\'' +
                '}';
    }
}
