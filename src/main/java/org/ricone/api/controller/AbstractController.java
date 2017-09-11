package org.ricone.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.security.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Paging getPaging()
    {
        return new Paging(request);
    }

    public AuthRequest getAuthRequest() throws ConfigException {
        return new AuthRequest(request);
    }
}
