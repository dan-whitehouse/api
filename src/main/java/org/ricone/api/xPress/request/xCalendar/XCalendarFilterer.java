package org.ricone.api.xPress.request.xCalendar;

import org.ricone.api.xPress.model.SessionList;
import org.ricone.api.xPress.model.XCalendar;
import org.ricone.api.xPress.model.XCalendars;
import org.ricone.authentication.MetaData;
import org.ricone.config.model.XCalendarFilter;
import org.springframework.stereotype.Component;

@Component("XCalendarFilterer")
public class XCalendarFilterer
{
    public XCalendarFilterer() {
    }
    
    public XCalendars apply(XCalendars instance, MetaData metaData) {
        for(XCalendar i : instance.getXCalendar()) {
            i = filter(i, null);
        }
        return instance;
    }
    
    public XCalendar apply(XCalendar instance, MetaData metaData) {
    	instance = filter(instance, null);
        return instance;
    }
    
    public XCalendar filter(XCalendar instance, XCalendarFilter filter) {
    	if(!filter.getRefId()) {
    		instance.setRefId(null);
    	}
    	if(!filter.getSchoolRefId()) {
    		instance.setSchoolRefId(null);
    	}
    	if(!filter.getSchoolYear()) {
    		instance.setSchoolYear(null);
    	}
	
    	//Sessions
    	for(SessionList i : instance.getSessions().getSessionList())
        {
    		if(!filter.getSessionssessionListdescription()) {
        		i.setDescription(null);
        	}
    		if(!filter.getSessionssessionListsessionCode()) {
        		i.setSessionCode(null);
        	}
    		if(!filter.getSessionssessionListsessionType()) {
        		i.setSessionType(null);
        	}
			if(!filter.getSessionssessionListlinkedSessionCode()) {
        		i.setLinkedSessionCode(null);
        	}
			if(!filter.getSessionssessionListmarkingTerm()) {
        		i.setMarkingTerm(null);
        	}
			if(!filter.getSessionssessionListschedulingTerm()) {
        		i.setSchedulingTerm(null);
        	}
			if(!filter.getSessionssessionListstartDate()) {
        		i.setStartDate(null);
        	}
			if(!filter.getSessionssessionListendDate()) {
        		i.setEndDate(null);
        	}
        }	
        return instance;
    }
}