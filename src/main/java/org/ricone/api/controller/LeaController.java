package org.ricone.api.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.ricone.api.model.Lea;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaController implements IController<Lea>
{
	//Reference for Request Mapping: http://www.baeldung.com/spring-requestmapping
	
	@Autowired
	LeaService service;
	
	@Override
    @RequestMapping(value= "/leas/{refId}", method = RequestMethod.GET)
    public Lea getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return service.getByRefId(refId);
    }
	 
	public Lea getSingle(Paging paging, @PathVariable(value="refId") String refId) throws Exception
    {
        return service.getByRefId(refId);
    }
	
	@Override
    @RequestMapping(value = "/leas", method = RequestMethod.GET) 
    public List<Lea> getMulti(HttpServletResponse response) throws Exception
    { 
    	List<Lea> leas = service.getLeas(); 
    	
    	//http://www.dotnetperls.com/lambda-java
    	//leas.stream().map((l) -> l.getLearefId() + "-000000001").forEach(System.out::println);
    	//leas.removeIf((l) -> l.getLeaid().equalsIgnoreCase("12345"));
    	response.setHeader("Controller", "Test");
        return leas;
    }
    
	@Override
    @RequestMapping(value= "/leas/{refId}/*", method = RequestMethod.GET)
    public List<Lea> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    { 
    	//System.out.println("wild card");
    	List<Lea> leas = service.getLeas(); 
        return leas;
    }
    
    
   
}