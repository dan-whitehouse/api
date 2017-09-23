package org.ricone.api.mapping.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.ricone.api.model.core.*;
import org.ricone.api.model.xpress.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XCalendarMapper")
public class XCalendarMapper {

    public XCalendarMapper() {
    }

    public XCalendarsResponse convert(List<SchoolCalendar> instance)
    {
        List<XCalendar> list = new ArrayList<>();
        for(SchoolCalendar calendar : instance)
        {
            XCalendar xCalendar = map(calendar);
            if (xCalendar != null) {
                list.add(xCalendar);
            }
        }

        XCalendarsResponse response = new XCalendarsResponse();
        XCalendars xCalendars = new XCalendars();
        xCalendars.setXCalendar(list);

        response.setXCalendars(xCalendars);
        return response;
    }

    public XCalendarResponse convert(SchoolCalendar instance)
    {
        XCalendarResponse response = new XCalendarResponse();
        XCalendar xCalendar = map(instance);
        if (xCalendar != null) {
            response.setXCalendar(xCalendar);
        }
        return response;
    }

    public XCalendar map(SchoolCalendar instance)
    {
        XCalendar xCalendar = new XCalendar();
        xCalendar.setRefId(instance.getSchoolCalendarRefId());
        xCalendar.setSchoolRefId(instance.getSchool().getSchoolRefId());
        xCalendar.setSchoolYear(instance.getCalendarYear());

        List<SessionList> sessionsList = new ArrayList<>();
        for(SchoolCalendarSession calendarSession : instance.getSchoolCalendarSessions())
        {
            SessionList sessionList = mapSessionList(calendarSession);
            if(sessionList != null){
                sessionsList.add(sessionList);
            }
        }

        if(CollectionUtils.isNotEmpty(sessionsList))
        {
            Sessions sessions = new Sessions();
            sessions.setSessionList(sessionsList);
            xCalendar.setSessions(sessions);
        }

        return xCalendar;
    }

    private SessionList mapSessionList(SchoolCalendarSession calendarSession)
    {
        SessionList sessionList = new SessionList();

        sessionList.setDescription(calendarSession.getDescription());
        sessionList.setSessionCode(calendarSession.getCode());
        sessionList.setSessionType(calendarSession.getSessionTypeCode());
        sessionList.setLinkedSessionCode(calendarSession.getLinkedSessionCode());

        sessionList.setMarkingTerm(BooleanUtils.toStringTrueFalse(calendarSession.getMarkingTermIndicator()));
        sessionList.setSchedulingTerm(BooleanUtils.toStringTrueFalse(calendarSession.getSchedulingTermIndicator()));

        sessionList.setStartDate(DateFormatUtils.format(calendarSession.getBeginDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        sessionList.setEndDate(DateFormatUtils.format(calendarSession.getEndDate(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));

        if(sessionList.isEmptyObject())
        {
            return null;
        }
        return sessionList;
    }
}
