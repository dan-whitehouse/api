package org.ricone.api.xPress.request.changesSince;

import org.ricone.api.core.model.*;
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
	public List<EventLog> findAllByLea(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByLea(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<LeaWrapper> leas = lea.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			leas.forEach(wrapper -> {
				if(wrapper.getLea().getLeaRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setLea(wrapper.getLea());
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllBySchool(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllBySchool(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<SchoolWrapper> schools = school.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			schools.forEach(wrapper -> {
				if(wrapper.getSchool().getSchoolRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setSchool(wrapper.getSchool());
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByCalendar(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByCalendar(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<SchoolCalendarWrapper> calendars = calendar.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			calendars.forEach(wrapper -> {
				if(wrapper.getSchoolCalendar().getSchoolCalendarRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setCalendar(wrapper.getSchoolCalendar());
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByCourse(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByCourse(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<CourseWrapper> courses = course.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			courses.forEach(wrapper -> {
				if(wrapper.getCourse().getCourseRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setCourse(wrapper.getCourse());
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByRoster(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByRoster(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<CourseSectionWrapper> courseSections = roster.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			courseSections.forEach(wrapper -> {
				if(wrapper.getCourseSection().getCourseSectionRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setRoster(wrapper.getCourseSection());
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByStaff(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByStaff(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<Staff> staffs = staff.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			staffs.forEach(wrapper -> {
				if(wrapper.getStaffRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setStaff(wrapper);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByStudent(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByStudent(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<Student> students = student.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			students.forEach(wrapper -> {
				if(wrapper.getStudentRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setStudent(wrapper);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByContact(MetaData metaData, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByContact(metaData, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<StudentContact> contacts = contact.findByRefIds(metaData, refIds);
		eventLogs.forEach(eventLog -> {
			contacts.forEach(wrapper -> {
				if(wrapper.getStudentContactRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setContact(wrapper);
				}
			});
		});

		return eventLogs;
	}
}
