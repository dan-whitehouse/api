package org.ricone.api.controller;

import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.Lea;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController 
{
	//Reference for Request Mapping: http://www.baeldung.com/spring-requestmapping
	
	@Autowired
	LeaService service;
	
    @RequestMapping(value = "*", method = RequestMethod.GET)
    public void notFound() throws NotFoundException 
    { 
        throw new NotFoundException("Try again....");
    }    
}