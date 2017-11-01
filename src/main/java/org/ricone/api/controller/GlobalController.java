package org.ricone.api.controller;

import org.ricone.api.exception.ForbiddenException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController 
{	
    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public void notFound() throws ForbiddenException
    { 
        throw new ForbiddenException("You shouldn't be here...");
    }
}