package org.ricone.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.api.exception.ConfigException;
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

   /* @Autowired
    private Pageable pageRequest;*/

    public AbstractController(){
    }

    /*public Pageable getPaging()
    {
        boolean hasPage = StringUtils.isNotBlank(request.getHeader("page"));
        boolean hasSize = StringUtils.isNotBlank(request.getHeader("size"));

        if(hasPage && hasSize)
        {
            int page = NumberUtils.toInt(request.getHeader("page"));
            int size = NumberUtils.toInt(request.getHeader("size"));

            return new PageRequest(page, size);
        }
        return null;
    }*/

    public AuthRequest getAuthRequest() throws ConfigException {
        return new AuthRequest(request);
    }
}
