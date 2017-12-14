package org.ricone.api.xPress.request.xSchool;

import org.ricone.api.xPress.model.OtherId;
import org.ricone.api.xPress.model.PhoneNumber;
import org.ricone.api.xPress.model.XSchool;
import org.ricone.api.xPress.model.XSchools;
import org.ricone.authentication.MetaData;
import org.ricone.config.model.XSchoolFilter;

public class XSchoolFilterer
{
    public XSchoolFilterer() {
    }
    
    public XSchools apply(XSchools instance, MetaData metaData)
    {
        for(XSchool i : instance.getXSchool())
        {
            i = filter(i, null);
        }
        return instance;
    }
    
    public XSchool apply(XSchool instance, MetaData metaData)
    {
    	instance = filter(instance, null);
        return instance;
    }
    
    public XSchool filter(XSchool instance, XSchoolFilter filter)
    {
    	if(!filter.getRefId()) {
    		instance.setRefId(null);
    	}
    	if(!filter.getSchoolName()) {
    		instance.setSchoolName(null);
    	}
    	if(!filter.getLeaRefId()) {
    		instance.setLeaRefId(null);
    	}

    	//Address
    	if(!instance.getAddress().isEmptyObject()) {
    		if(!filter.getAddressaddressType()) {
        		instance.getAddress().setAddressType(null);
        	}
    		if(!filter.getAddresscity()) {
    			instance.getAddress().setCity(null);
        	}
    		if(!filter.getAddresscountryCode()) {
    			instance.getAddress().setCountryCode(null);
        	}
			if(!filter.getAddressline1()) {
				instance.getAddress().setLine1(null);
        	}
			if(!filter.getAddressline2()) {
				instance.getAddress().setLine2(null);
        	}
			if(!filter.getAddresspostalCode()) {
				instance.getAddress().setPostalCode(null);
        	}
			if(!filter.getAddressstateProvince()) {
				instance.getAddress().setStateProvince(null);
        	}
    	}
    	
    	//Primary Phone Number
    	if(!instance.getPhoneNumber().isEmptyObject()) {
    		if(!filter.getPhoneNumbernumber()) {
        		instance.getPhoneNumber().setNumber(null);
        	}
    		if(!filter.getPhoneNumberphoneNumberType()) {
        		instance.getPhoneNumber().setPhoneNumberType(null);
        	}
    		if(!filter.getPhoneNumberprimaryIndicator()) {
        		instance.getPhoneNumber().setPrimaryIndicator(null);
        	}
    	}
    	
    	//Other Phone Numbers
    	for(PhoneNumber i : instance.getOtherPhoneNumbers().getPhoneNumber()) {
    		if(!filter.getOtherPhoneNumbersphoneNumbernumber()) {
        		i.setNumber(null);
        	}
    		if(!filter.getOtherPhoneNumbersphoneNumberphoneNumberType()) {
        		i.setPhoneNumberType(null);
        	}
    		if(!filter.getOtherPhoneNumbersphoneNumberprimaryIndicator()) {
        		i.setPrimaryIndicator(null);
        	}
        }
    	
    	//Grade Levels
    	if(!filter.getGradeLevels()) {
    		instance.setGradeLevels(null);
    	}
    	
    	//Identifiers
    	if(!filter.getLocalId()) {
    		instance.setLocalId(null);
    	}
    	if(!filter.getStateProvinceId()) {
    		instance.setStateProvinceId(null);
    	}
    	
    	//Other Identifiers
    	for(OtherId i : instance.getOtherIds().getOtherId()) {
    		if(!filter.getOtherIdsotherIdid()) {
        		i.setId(null);
        	}
    		if(!filter.getOtherIdsotherIdtype()) {
        		i.setType(null);
        	}
        }
    	
        return instance;
    }
}