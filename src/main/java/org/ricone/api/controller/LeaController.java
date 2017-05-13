package org.ricone.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import org.ricone.api.model.Lea;
import org.ricone.api.model.xpress.XLea;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaController //implements IController<Lea>
{
	//Reference for Request Mapping: http://www.baeldung.com/spring-requestmapping
	
	@Autowired
	LeaService service;
	
//	@Override
    @RequestMapping(value= "/leas/{refId}", method = RequestMethod.GET)
    public Lea getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return service.getByRefId(refId);
    }
	 
	public Lea getSingle(Paging paging, @PathVariable(value="refId") String refId) throws Exception
    {
        return service.getByRefId(refId);
    }
	
//	@Override
    @RequestMapping(value = "/leas", method = RequestMethod.GET) 
    public List<XLea> getMulti(HttpServletResponse response) throws Exception
    { 
    	//Get data from DB.
    	List<Lea> leas = service.getLeas();
    	
    	//Map list of Leas
    	List<XLea> xLeas = new ArrayList<>();
        leas.stream().filter(l -> l != null).forEach((lea) -> { xLeas.add(map(lea)); });
    	response.setHeader("Controller", "Test");
        return xLeas;
    }
	
	private XLea map(Lea lea) 
	{
		XLea xlea = new XLea();
		xlea.setRefId(lea.getLearefId());
		return xlea;
	}
	
//@Override
    @RequestMapping(value= "/leas/{refId}/*", method = RequestMethod.GET)
    public List<Lea> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    { 
    	//System.out.println("wild card");
    	List<Lea> leas = service.getLeas(); 
        return leas;
    }
    
    
   
}