package org.ricone.api.xPress.request.xStaff;

import org.ricone.api.xPress.model.OtherId;
import org.ricone.api.xPress.model.StaffPersonAssignment;
import org.ricone.api.xPress.model.XStaff;
import org.ricone.api.xPress.model.XStaffs;
import org.ricone.authentication.MetaData;
import org.ricone.config.model.XStaffFilter;
import org.springframework.stereotype.Component;

@Component("XStaffFilterer")
public class XStaffFilterer
{
    public XStaffFilterer() {
    }
    
    public XStaffs apply(XStaffs instance, MetaData metaData) {
        for(XStaff i : instance.getXStaff()) {
            i = filter(i, null);
        }
        return instance;
    }
    
    public XStaff apply(XStaff instance, MetaData metaData) {
    	instance = filter(instance, null);
        return instance;
    }
    
    public XStaff filter(XStaff instance, XStaffFilter filter) {
    	if(!filter.getRefId()) {
    		instance.setRefId(null);
    	}
    	if(!filter.getSex()) {
    		instance.setSex(null);
    	}
    	
    	//Name
    	if(!instance.getName().isEmptyObject()) {
    		if(!filter.getNamefamilyName()) {
        		instance.getName().setFamilyName(null);
        	}
    		if(!filter.getNamegivenName()) {
        		instance.getName().setGivenName(null);
        	}
    		if(!filter.getNamemiddleName()) {
        		instance.getName().setMiddleName(null);
        	}
    		if(!filter.getNameprefix()) {
        		instance.getName().setPrefix(null);
        	}
    		if(!filter.getNamesuffix()) {
        		instance.getName().setSuffix(null);
        	}
    		if(!filter.getNametype()) {
        		instance.getName().setType(null);
        	}
    	}
    	
    	//Email
    	if(!instance.getEmail().isEmptyObject()) {
    		if(!filter.getEmailemailType()) {
        		instance.getEmail().setEmailType(null);
        	}
    		if(!filter.getEmailemailAddress()) {
        		instance.getEmail().setEmailAddress(null);
        	}
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
    	
    	//Assignments
    	if(instance.getPrimaryAssignment() != null) {
    		if(!filter.getPrimaryAssignmentleaRefId()) {
    			instance.getPrimaryAssignment().setLeaRefId(null);
        	}
    		if(!filter.getPrimaryAssignmentschoolRefId()) {
    			instance.getPrimaryAssignment().setSchoolRefId(null);
        	}
    		if(!filter.getPrimaryAssignmentschoolRefId()) {
    			instance.getPrimaryAssignment().setSchoolRefId(null);
        	}
    	}
    	
    	//Other Assignments
    	for(StaffPersonAssignment i : instance.getOtherAssignments().getStaffPersonAssignment()) {
    		if(!filter.getOtherAssignmentsstaffPersonAssignmentleaRefId()) {
    			i.setLeaRefId(null);
        	}
    		if(!filter.getOtherAssignmentsstaffPersonAssignmentschoolRefId()) {
    			i.setSchoolRefId(null);
        	}
    		if(!filter.getOtherAssignmentsstaffPersonAssignmentjobFunction()) {
    			i.setJobFunction(null);
        	}
        }
        return instance;
    }
}