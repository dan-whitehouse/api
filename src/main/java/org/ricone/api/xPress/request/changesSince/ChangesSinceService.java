package org.ricone.api.xPress.request.changesSince;

import org.ricone.api.core.model.EventType;
import org.ricone.api.core.model.wrapper.*;
import org.ricone.api.xPress.request.xCalendar.ICalendarDAO;
import org.ricone.api.xPress.request.xContact.IContactDAO;
import org.ricone.api.xPress.request.xCourse.ICourseDAO;
import org.ricone.api.xPress.request.xLea.ILeaDAO;
import org.ricone.api.xPress.request.xRoster.IRosterDAO;
import org.ricone.api.xPress.request.xSchool.ISchoolDAO;
import org.ricone.api.xPress.request.xStaff.IStaffDAO;
import org.ricone.api.xPress.request.xStudent.IStudentDAO;
import org.ricone.authentication.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("changesSinceService")
@Transactional
public class ChangesSinceService implements IChangesSinceService
{
	@Autowired
	private IEventLogDAO dao;

	@Autowired
	private ILeaDAO lea;

	@Autowired
	private ISchoolDAO school;

	@Autowired
	private ICalendarDAO calendar;

	@Autowired
	private ICourseDAO course;

	@Autowired
	private IRosterDAO roster;

	@Autowired
	private IStaffDAO staff;

	@Autowired
	private IStudentDAO student;

	@Autowired
	private IContactDAO contact;

	@Override
	public List<EventLogWrapper> findAllByLea(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByLea(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<LeaWrapper> leas = lea.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> leas.forEach(wrapper -> {
            if(wrapper.getLea().getLeaRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setLea(wrapper.getLea());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllBySchool(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllBySchool(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<SchoolWrapper> schools = school.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> schools.forEach(wrapper -> {
            if(wrapper.getSchool().getSchoolRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setSchool(wrapper.getSchool());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllByCalendar(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByCalendar(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<SchoolCalendarWrapper> calendars = calendar.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> calendars.forEach(wrapper -> {
            if(wrapper.getSchoolCalendar().getSchoolCalendarRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setCalendar(wrapper.getSchoolCalendar());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllByCourse(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByCourse(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<CourseWrapper> courses = course.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> courses.forEach(wrapper -> {
            if(wrapper.getCourse().getCourseRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setCourse(wrapper.getCourse());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllByRoster(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByRoster(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<CourseSectionWrapper> courseSections = roster.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> courseSections.forEach(wrapper -> {
            if(wrapper.getCourseSection().getCourseSectionRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setRoster(wrapper.getCourseSection());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllByStaff(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByStaff(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<StaffWrapper> staffs = staff.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> staffs.forEach(wrapper -> {
            if(wrapper.getStaff().getStaffRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setStaff(wrapper.getStaff());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllByStudent(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByStudent(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<StudentWrapper> students = student.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> students.forEach(wrapper -> {
            if(wrapper.getStudent().getStudentRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setStudent(wrapper.getStudent());
            }
        }));

		return eventLogs;
	}

	@Override
	public List<EventLogWrapper> findAllByContact(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLogWrapper> eventLogs = dao.findAllByContact(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventLog().getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getEventLog().getObjectRefId());
			}
		});

		List<StudentContactWrapper> contacts = contact.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> contacts.forEach(wrapper -> {
            if(wrapper.getStudentContact().getStudentContactRefId().equalsIgnoreCase(eventLog.getEventLog().getObjectRefId())){
                eventLog.getEventLog().setContact(wrapper.getStudentContact());
            }
        }));

		return eventLogs;
	}
}
