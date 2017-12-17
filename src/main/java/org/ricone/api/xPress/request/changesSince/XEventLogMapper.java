package org.ricone.api.xPress.request.changesSince;

import org.ricone.api.core.model.EventLog;
import org.ricone.api.core.model.EventObject;
import org.ricone.api.core.model.EventType;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xPress.model.*;
import org.ricone.api.xPress.request.xCalendar.XCalendarMapper;
import org.ricone.api.xPress.request.xContact.XContactMapper;
import org.ricone.api.xPress.request.xCourse.XCourseMapper;
import org.ricone.api.xPress.request.xLea.XLeaMapper;
import org.ricone.api.xPress.request.xRoster.XRosterMapper;
import org.ricone.api.xPress.request.xSchool.XSchoolMapper;
import org.ricone.api.xPress.request.xStaff.XStaffMapper;
import org.ricone.api.xPress.request.xStudent.XStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XEventLogMapper")
public class XEventLogMapper {

    @Autowired
    private XLeaMapper xLeaMapper;

    @Autowired
    private XSchoolMapper xSchoolMapper;

    @Autowired
    private XCalendarMapper xCalendarMapper;

    @Autowired
    private XCourseMapper xCourseMapper;

    @Autowired
    private XRosterMapper xRosterMapper;

    @Autowired
    private XStaffMapper xStaffMapper;

    @Autowired
    private XStudentMapper xStudentMapper;

    @Autowired
    private XContactMapper xContactMapper;

    public XChangesSinceResponse convert(List<EventLogWrapper> instance) {
        List<XChangeSince> list = new ArrayList<>();
        for(EventLogWrapper wrapper : instance)
        {
            XChangeSince xChangeSince = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xChangeSince != null) {
                list.add(xChangeSince);
            }
        }

        XChangesSinceResponse response = new XChangesSinceResponse();
        XChangesSince xChangesSince = new XChangesSince();
        xChangesSince.setXChangeSince(list);

        response.setXChangesSince(xChangesSince);
        return response;
    }

    private XChangeSince map(EventLog eventLog, String districtId) {

        XChangeSince xChangeSince = new XChangeSince();
        xChangeSince.setRefId(eventLog.getEventRefId());
        //xChangeSince.setEventObject(eventLog.getObject());
        xChangeSince.setEventType(eventLog.getEventType());
        xChangeSince.setEventDate(eventLog.getEventTimestamp().toString());

        if(eventLog.getObject().equals(EventObject.LEA)){
            mapXLea(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.SCHOOL)){
            mapXSchool(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.CALENDAR)){
            mapXCalendar(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.COURSE)){
            mapXCourse(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.ROSTER)){
            mapXRoster(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.STAFF)){
            mapXStaff(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.STUDENT)){
            mapXStudent(xChangeSince, eventLog, districtId);
        }
        else  if(eventLog.getObject().equals(EventObject.CONTACT)){
            mapXContact(xChangeSince, eventLog, districtId);
        }
        return xChangeSince;
    }

    private void mapXLea(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XLea xLea;
        if(eventLog.getLea() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xLea = xLeaMapper.map(eventLog.getLea(), districtId);
        }
        else {
            xLea = new XLea(eventLog.getObjectRefId());
        }
        xChangeSince.setXLea(xLea);
    }

    private void mapXSchool(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XSchool xSchool;
        if(eventLog.getSchool() != null & !eventLog.getEventType().equals(EventType.DELETE)) {
            xSchool = xSchoolMapper.map(eventLog.getSchool(), districtId);
        }
        else {
            xSchool = new XSchool(eventLog.getObjectRefId());
        }
        xChangeSince.setXSchool(xSchool);
    }

    private void mapXCalendar(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XCalendar xCalendar;
        if(eventLog.getCalendar() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xCalendar = xCalendarMapper.map(eventLog.getCalendar(), districtId);
        }
        else {
            xCalendar = new XCalendar(eventLog.getObjectRefId());
        }
        xChangeSince.setXCalendar(xCalendar);
    }

    private void mapXCourse(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XCourse xCourse;
        if(eventLog.getCourse() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xCourse = xCourseMapper.map(eventLog.getCourse(), districtId);
        }
        else {
            xCourse = new XCourse(eventLog.getObjectRefId());
        }
        xChangeSince.setXCourse(xCourse);
    }

    private void mapXRoster(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XRoster xRoster;
        if(eventLog.getRoster() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xRoster = xRosterMapper.map(eventLog.getRoster(), districtId);
        }
        else {
            xRoster = new XRoster(eventLog.getObjectRefId());
        }
        xChangeSince.setXRoster(xRoster);
    }

    private void mapXStaff(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XStaff xStaff;
        if(eventLog.getStaff() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xStaff = xStaffMapper.map(eventLog.getStaff(), districtId);
        }
        else {
            xStaff = new XStaff(eventLog.getObjectRefId());
        }
        xChangeSince.setxStaff(xStaff);
    }

    private void mapXStudent(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XStudent xStudent;
        if(eventLog.getStudent() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xStudent = xStudentMapper.map(eventLog.getStudent(), districtId);
        }
        else {
            xStudent = new XStudent(eventLog.getObjectRefId());
        }
        xChangeSince.setXStudent(xStudent);
    }

    private void mapXContact(XChangeSince xChangeSince, EventLog eventLog, String districtId) {
        XContact xContact;
        if(eventLog.getContact() != null && !eventLog.getEventType().equals(EventType.DELETE)) {
            xContact = xContactMapper.map(eventLog.getContact(), districtId);
        }
        else {
            xContact = new XContact(eventLog.getObjectRefId());
        }
        xChangeSince.setXContact(xContact);
    }
}
