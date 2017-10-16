package org.ricone.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.exception.ForbiddenException;
import org.ricone.api.security.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @project: ricone
 * @author: Dan on 9/10/2017.
 */
public abstract class AbstractController
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public AbstractController(){
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

            return new PageRequest(page, size);
        }
        return pageRequest;
    }

    public AuthRequest getAuthRequest() throws ConfigException {
        return new AuthRequest(request);
    }
}
