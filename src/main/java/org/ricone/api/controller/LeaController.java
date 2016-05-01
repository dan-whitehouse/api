package org.ricone.api.controller;

import java.util.List;
import java.util.UUID;

import org.ricone.api.model.Lea;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaController 
{
	//Reference for Request Mapping: http://www.baeldung.com/spring-requestmapping
	
	@Autowired
	LeaService service;
	
    @RequestMapping(value = "/leas", method = RequestMethod.GET) 
    public List<Lea> getLeas() throws Exception
    { 
        return service.getLeas();
    }
    
    @RequestMapping(value= "/leas/{refId}", method = RequestMethod.GET)
    public Lea getLeaByRefId(@PathVariable(value="refId") UUID refId) throws Exception
    {
        return service.getByRefId(refId.toString());
    }   
}