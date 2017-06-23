package org.ricone.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.ricone.api.model.Lea;
import org.ricone.api.model.LeaTelephone;
import org.ricone.api.model.School;
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

    @RequestMapping(value= "/requests/leas/{refId}", method = RequestMethod.GET)
    public Lea getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {

        Lea instance = service.getByRefId(refId);

        //Prepare For Output
        Lea lea = map(instance);
        return lea;
    }

    @RequestMapping(value = "/requests/leas", method = RequestMethod.GET)
    public List<Lea> getMulti(HttpServletResponse response) throws Exception
    { 
    	//Get data from DB.
    	List<Lea> instance = service.getLeas();

    	//Prepare For Output
        List<Lea> leas = new ArrayList<>();
        instance.stream().filter(l -> l != null).forEach((lea) -> { leas.add(map(lea)); });

    	return leas;
    }

    @RequestMapping(value= "/requests/leas/{refId}/*", method = RequestMethod.GET)
    public List<Lea> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
    	List<Lea> leas = service.getLeas(); 
        return leas;
    }

    private Lea map(Lea instance)
    {
        Lea lea = new Lea();
        lea.setLeaRefId(instance.getLeaRefId());
        lea.setLeaName(instance.getLeaName());
        lea.setLeaId(instance.getLeaId());
        lea.setLeaSeaId(instance.getLeaSeaId());
        lea.setLeaNcesId(instance.getLeaNcesId());
        lea.setStreetNumberAndName(instance.getStreetNumberAndName());
        lea.setLine2(instance.getLine2());
        lea.setCity(instance.getCity());
        lea.setStateCode(instance.getStateCode());
        lea.setPostalCode(instance.getPostalCode());
        lea.setAddressCountyName(instance.getAddressCountyName());
        lea.setCountryCode(instance.getCountryCode());

        for(LeaTelephone phone : instance.getLeaTelephones())
        {
            lea.getLeaTelephones().add(phone);
        }
        for(School school : instance.getSchools())
        {
            lea.getSchools().add(new School(school.getSchoolRefId(), school.getSchoolName()));
        }
        return lea;
    }

    
   
}