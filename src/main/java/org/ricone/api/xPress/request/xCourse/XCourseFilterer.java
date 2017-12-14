package org.ricone.api.xPress.request.xCourse;

import org.ricone.api.xPress.model.OtherId;
import org.ricone.api.xPress.model.XCourse;
import org.ricone.api.xPress.model.XCourses;
import org.ricone.authentication.MetaData;
import org.ricone.config.model.XCourseFilter;

public class XCourseFilterer
{
    public XCourseFilterer() {
    }
    
    public XCourses apply(XCourses instance, MetaData metaData) {
        for(XCourse i : instance.getXCourse()) {
            i = filter(i, null);
        }
        return instance;
    }
    
    public XCourse apply(XCourse instance, MetaData metaData) {
    	instance = filter(instance, null);
        return instance;
    }
    
    public XCourse filter(XCourse instance, XCourseFilter filter) {
    	if(!filter.getRefId()) {
    		instance.setRefId(null);
    	}
    	if(!filter.getCourseTitle()) {
    		instance.setCourseTitle(null);
    	}
    	if(!filter.getSubject()) {
    		instance.setSubject(null);
    	}
    	if(!filter.getDescription()) {
    		instance.setDescription(null);
    	}
    	if(!filter.getSchoolRefId()) {
    		instance.setSchoolRefId(null);
    	}
    	if(!filter.getScedCourseCode()) {
    		instance.setScedCourseCode(null);
    	}
    	if(!filter.getScedCourseLevelCode()) {
    		instance.setScedCourseLevelCode(null);
    	}
    	if(!filter.getScedCourseSubjectAreaCode()) {
    		instance.setScedCourseSubjectAreaCode(null);
    	}
    	
    	//Identifiers
    	if(!filter.getLeaCourseId()) {
    		instance.setLeaCourseId(null);
    	}
    	if(!filter.getSchoolCourseId()) {
    		instance.setSchoolCourseId(null);
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
    	
    	//Applicable Education Levels
    	if(!filter.getApplicableEducationLevelsapplicableEducationLevel()) {
    		instance.setApplicableEducationLevels(null);
    	}

        return instance;
    }
}