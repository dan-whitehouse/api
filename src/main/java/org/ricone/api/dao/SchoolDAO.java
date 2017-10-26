package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("schoolDAO")
@SuppressWarnings("unchecked")
public class SchoolDAO extends AbstractDAO<Integer, School> implements ISchoolDAO
{
	private final String PRIMARY_KEY = "schoolRefId";
	private final String LOCAL_ID_KEY = "schoolId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";

	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<School> findAll(Pageable pageRequest) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(lea.get("leaRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByCalendarRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) from.<School, SchoolCalendar>fetch("schoolCalendars", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByCourseRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>fetch("courses", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(courses.get("courseRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByRosterRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>fetch("courses", JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>fetch("courseSections", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(courseSections.get("courseSectionRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByStaffRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) from.<School, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, Staff> staff = (Join<StaffAssignment, Staff>) staffAssignments.<StaffAssignment, Staff>fetch("staff", JoinType.INNER);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(staff.get("staffRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>fetch("student", JoinType.INNER);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(student.get("studentRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<School> findAllByContactRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>fetch("student", JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>fetch("studentContactRelationships", JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = (Join<StudentContactRelationship, StudentContact>) studentContactRelationships.<StudentContactRelationship, StudentContact>fetch("studentContact", JoinType.INNER);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(contact.get("studentContactRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<School> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<School> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public School findByRefId(String refId) throws Exception {
		//TODO - Not Working
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));

		Query<School> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public School findByLocalId(String localId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.and(cb.equal(schoolIdentifiers.get(LOCAL_ID_KEY), localId), cb.equal(schoolIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "LEA")));

		Query<School> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public School findByBEDSId(String localId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<School> select = cb.createQuery(School.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>fetch("schoolGrades", JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>fetch("schoolTelephones", JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>fetch("schoolIdentifiers", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.and(cb.equal(schoolIdentifiers.get(LOCAL_ID_KEY), localId), cb.equal(schoolIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "BEDS")));

		Query<School> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public void save(School instance) {
		super.persist(instance);
	}

	@Override
	public void update(School instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(School instance) {
		super.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		School instance = (School)criteria.uniqueResult();
		super.delete(instance);
	}
}
