package org.ricone.api.xPress.request.xRoster;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.core.dao.*;
import org.ricone.authentication.MetaData;
import org.ricone.exception.NoContentException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("rosterDAO")
@SuppressWarnings("unchecked")
public class RosterDAO extends AbstractDAO<Integer, CourseSection> implements IRosterDAO
{
	private final String PRIMARY_KEY = "courseSectionRefId";
	private final String LEA_LOCAL_ID_KEY = "leaId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<CourseSection> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<CourseSection> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getCourse().getSchool().getLea());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<CourseSection> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where();
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		//TODO - Implement this isPaged check on all other DAO methods for each class
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<CourseSection> findAllByCourseRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(course.get("courseRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		//TODO - Implement this isPaged check on all other DAO methods for each class
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<CourseSection> findAllByStaffRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = (Join<StaffCourseSection, Staff>)staffCourseSections.<StaffCourseSection, Staff>fetch("staff", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);

		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(staff.get("staffRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		//TODO - Implement this isPaged check on all other DAO methods for each class
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<CourseSection> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>)studentCourseSections.<StudentEnrollment, Student>fetch("student", JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>fetch("studentIdentifiers", JoinType.LEFT);

		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(student.get("studentRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		//TODO - Implement this isPaged check on all other DAO methods for each class
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<CourseSection> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<CourseSection> q = getSession().createQuery(select);
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
			Hibernate.initialize(sc.getCourse().getSchool().getLea());
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public CourseSection findByRefId(MetaData metaData, String refId) throws Exception
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>)from.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final Join<Course, School> school = (Join<Course, School>)course.<Course, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);


		final Join<CourseSection, SchoolCalendarSession> schoolCalendarSession = from.join("schoolCalendarSession", JoinType.LEFT);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = schoolCalendarSession.join("schoolCalendar", JoinType.LEFT);
		final SetJoin<CourseSection, CourseSectionSchedule> courseSectionSchedules = (SetJoin<CourseSection, CourseSectionSchedule>) from.<CourseSection, CourseSectionSchedule>join("courseSectionSchedules", JoinType.LEFT);

		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join("staff", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);

		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>join("studentCourseSections", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentCourseSections.join("student", JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>join("studentIdentifiers", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<CourseSection> q = getSession().createQuery(select);
		CourseSection instance = q.getSingleResult();

		Hibernate.initialize(instance.getCourse());
		Hibernate.initialize(instance.getCourse().getSchool());
		Hibernate.initialize(instance.getSchoolCalendarSession());
		Hibernate.initialize(instance.getSchoolCalendarSession().getSchoolCalendar());
		Hibernate.initialize(instance.getCourseSectionSchedules());
		Hibernate.initialize(instance.getStaffCourseSections());
		instance.getStaffCourseSections().forEach(tcs -> {
			Hibernate.initialize(tcs.getStaff());
			Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
		});
		Hibernate.initialize(instance.getStudentCourseSections());
		instance.getStudentCourseSections().forEach(scs -> {
			Hibernate.initialize(scs.getStudent());
			Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
		});

		return instance;
	}

	@Override
	public void save(CourseSection instance) {
		super.persist(instance);
	}

	@Override
	public void update(CourseSection instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(CourseSection instance) {
		super.delete(instance);
	}
}