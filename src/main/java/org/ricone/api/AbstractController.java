package org.ricone.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.api.util.Util;
import org.ricone.authentication.AuthRequest;
import org.ricone.authentication.DecodedToken;
import org.ricone.authentication.MetaData;
import org.ricone.authentication.TokenDecoder;
import org.ricone.authentication.session.Session;
import org.ricone.authentication.session.SessionManager;
import org.ricone.exception.ConfigException;
import org.ricone.exception.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class AbstractController
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public AbstractController(){
    }

    public MetaData getMetaData(Pageable pageRequest) throws Exception
    {
        AuthRequest authRequest = new AuthRequest(request);
        DecodedToken token = TokenDecoder.decodeToken(authRequest.getToken());
        Session session = SessionManager.getInstance().getSession(token.getApplication_id());

        Pageable pageable = getPaging(pageRequest);
        Map<String, String> headers = Util.getHeaders(request);

        MetaData metaData = new MetaData();
        metaData.setApp(session.getApp());
        metaData.setToken(session.getToken());
        metaData.setHeaders(headers);
        metaData.setPaging(pageable);

        return metaData;
    }

    public Pageable getPaging(Pageable pageRequest) throws Exception
    {
        boolean hasPage = StringUtils.isNotBlank(request.getHeader("page"));
        boolean hasSize = StringUtils.isNotBlank(request.getHeader("size"));

        if(hasPage && hasSize)
        {
            Integer page = NumberUtils.toInt(request.getHeader("page"));
            Integer size = NumberUtils.toInt(request.getHeader("size"));

            if(size != null && size < 1) throw new ForbiddenException("Page size must not be less than one!");
            return PageRequest.of(page, size);
        }

        if(pageRequest.isUnpaged()) {
            return Pageable.unpaged();
        }

        return pageRequest;
    }

    public AuthRequest getAuthRequest() throws ConfigException {
        return new AuthRequest(request);
    }

}
