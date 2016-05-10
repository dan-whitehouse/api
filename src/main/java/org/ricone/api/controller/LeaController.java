package org.ricone.api.controller;

import java.util.List;
import java.util.UUID;

import org.ricone.api.model.Lea;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    	List<Lea> leas = service.getLeas(); 
    	
    	//http://www.dotnetperls.com/lambda-java
    	//leas.stream().map((l) -> l.getLearefId() + "-000000001").forEach(System.out::println);
    	//leas.removeIf((l) -> l.getLeaid().equalsIgnoreCase("12345"));
        return leas;
    }
    
    @RequestMapping(value = "/leas", params = { "pageNum", "pageSize" }, method = RequestMethod.GET) 
    public List<Lea> getLeas(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception
    { 
    	System.out.println(pageNum + " | " + pageSize);
    	 
    	
    	
    	
        return service.getLeas();
    }
    
    
    
    @RequestMapping(value= "/leas/{refId}", method = RequestMethod.GET)
    public Lea getLeaByRefId(@PathVariable(value="refId") UUID refId) throws Exception
    {
        return service.getByRefId(refId.toString());
    }   
}