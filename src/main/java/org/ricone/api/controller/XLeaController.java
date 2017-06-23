package org.ricone.api.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.model.Lea;
import org.ricone.api.model.LeaTelephone;

import org.ricone.api.model.xpress.*;
import org.ricone.api.service.LeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class XLeaController //implements IController<XLea>
{
	//Reference for Request Mapping: http://www.baeldung.com/spring-requestmapping
	
	@Autowired
	LeaService service;

    @RequestMapping(value= "/requests/xLeas/{refId}", method = RequestMethod.GET)
    public XLeaResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        Lea lea = service.getByRefId(refId);

        XLeaResponse out = new XLeaResponse();
        out.setXLea(map(lea));
        return out;
    }

    @RequestMapping(value = "/requests/xLeas", method = RequestMethod.GET)
    public XLeasResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<Lea> leas = service.getLeas();

        List<XLea> list = new ArrayList<>();
        for(Lea lea : leas) {
            XLea xLea = map(lea);
            if (xLea != null) {
                list.add(xLea);
            }
        }

        XLeasResponse xLeasResponse = new XLeasResponse();
        XLeas xLeas = new XLeas();
        xLeas.setXLea(list);
        xLeasResponse.setXLeas(xLeas);
    	return xLeasResponse;
    }

    @RequestMapping(value= "/requests/xLeas/{refId}/*", method = RequestMethod.GET)
    public List<XLea> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
    	List<Lea> leas = service.getLeas(); 
        return null;
    }

    private XLea map(Lea instance)
    {
        XLea xLea = new XLea();
        xLea.setRefId(instance.getLeaRefId());
        xLea.setLeaName(instance.getLeaName());
        xLea.setLocalId(instance.getLeaId());
        xLea.setStateProvinceId(instance.getLeaSeaId());
        xLea.setNcesId(instance.getLeaNcesId());

        //Address
        Address address = mapAddress(instance);
        xLea.setAddress(address);

        //PhoneNumber - Primary
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for(LeaTelephone telephone : instance.getLeaTelephones())
        {
            PhoneNumber phone = mapPhone(telephone);
            if(telephone.getPrimaryTelephoneNumberIndicator())
            {
                xLea.setPhoneNumber(phone);
            }
            else
            {
                phoneNumbers.add(phone);
            }
        }

        //PhoneNumbers - Other
        if(CollectionUtils.isNotEmpty(phoneNumbers))
        {
            OtherPhoneNumbers otherPhoneNumbers = new OtherPhoneNumbers();
            otherPhoneNumbers.setPhoneNumber(phoneNumbers);
            xLea.setOtherPhoneNumbers(otherPhoneNumbers);
        }

        return xLea;
    }

    private Address mapAddress(Lea lea)
    {
        Address address = new Address();
        address.setAddressType(lea.getAddressType());
        address.setLine1(lea.getStreetNumberAndName());
        address.setLine2(lea.getLine2());
        address.setPostalCode(lea.getPostalCode());
        address.setCity(lea.getCity());
        address.setCountryCode(lea.getCountryCode());
        address.setStateProvince(lea.getStateCode());

        if(address.isEmptyObject())
        {
            return null;
        }
        return address;
    }

    private PhoneNumber mapPhone(LeaTelephone telephone)
    {
        PhoneNumber phone = new PhoneNumber();
        phone.setNumber(telephone.getTelephoneNumber());
        phone.setPhoneNumberType(telephone.getTelephoneNumberTypeCode());
        phone.setPrimaryIndicator(BooleanUtils.toStringTrueFalse(telephone.getPrimaryTelephoneNumberIndicator()));

        if(phone.isEmptyObject())
        {
            return null;
        }
        return phone;
    }
}