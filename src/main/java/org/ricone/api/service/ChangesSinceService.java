package org.ricone.api.service;

import org.ricone.api.dao.*;
import org.ricone.api.model.core.*;
import org.ricone.api.model.core.extension.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    IEventLogDAO dao;

	@Autowired
	ILeaDAO lea;

	@Autowired
	ISchoolDAO school;

	@Autowired
	ICalendarDAO calendar;

	@Autowired
	ICourseDAO course;

	@Autowired
	IRosterDAO roster;

	@Autowired
	IStaffDAO staff;

	@Autowired
	IStudentDAO student;

	@Autowired
	IContactDAO contact;


	@Override
	public List<EventLog> findAllByLea(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByLea(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<Lea> leas = lea.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			leas.forEach(lea -> {
				if(lea.getLeaRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setLea(lea);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllBySchool(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllBySchool(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<School> schools = school.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			schools.forEach(school -> {
				if(school.getSchoolRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setSchool(school);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByCalendar(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByCalendar(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<SchoolCalendar> calendars = calendar.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			calendars.forEach(calendar -> {
				if(calendar.getSchoolCalendarRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setCalendar(calendar);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByCourse(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByCourse(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<Course> courses = course.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			courses.forEach(course -> {
				if(course.getCourseRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setCourse(course);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByRoster(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByRoster(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<CourseSection> courseSections = roster.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			courseSections.forEach(courseSection -> {
				if(courseSection.getCourseSectionRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setRoster(courseSection);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByStaff(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByStaff(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<Staff> staffs = staff.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			staffs.forEach(staff -> {
				if(staff.getStaffRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setStaff(staff);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByStudent(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByStudent(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<Student> students = student.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			students.forEach(student -> {
				if(student.getStudentRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setStudent(student);
				}
			});
		});

		return eventLogs;
	}

	@Override
	public List<EventLog> findAllByContact(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<EventLog> eventLogs = dao.findAllByContact(pageRequest, iso8601);
		eventLogs.forEach(eventLog -> {
			if(!eventLog.getEventType().equals(EventType.DELETE)) {
				refIds.add(eventLog.getObjectRefId());
			}
		});

		List<StudentContact> contacts = contact.findByRefIds(refIds);
		eventLogs.forEach(eventLog -> {
			contacts.forEach(contact -> {
				if(contact.getStudentContactRefId().equalsIgnoreCase(eventLog.getObjectRefId())){
					eventLog.setContact(contact);
				}
			});
		});

		return eventLogs;
	}
}
