package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.model.core.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("leaDAO")
@SuppressWarnings("unchecked")
public class LeaDAO extends AbstractDAO<Integer, Lea> implements ILeaDAO
{
	//https://stackoverflow.com/questions/28376121/jpa-2-criteriaquery-do-not-discriminate-child-entities

	private final String PRIMARY_KEY = "leaRefId";
	private final String LOCAL_ID_KEY = "leaId";

	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Lea> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		//TODO - Implement this where clause everywhere (modifying existing ones of course)
		select.where(from.get(LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(schools.get("schoolRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>fetch("schoolCalendars", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllByCourseRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>fetch("courses", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(courses.get("courseRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>fetch("courses", JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>fetch("courseSections", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(courseSections.get("courseSectionRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllByStaffRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>fetch("staffAssignments", JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = (Join<StaffAssignment, Staff>) staffAssignments.<StaffAssignment, Staff>fetch("staff", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(staff.get("staffRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>fetch("studentEnrollments", JoinType.INNER);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>fetch("student", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(student.get("studentRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findAllByContactRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>fetch("studentEnrollments", JoinType.INNER);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>fetch("student", JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>fetch("studentContactRelationships", JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = (Join<StudentContactRelationship, StudentContact>) studentContactRelationships.<StudentContactRelationship, StudentContact>fetch("studentContact", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(contact.get("studentContactRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Lea> findByRefIds(Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(from.get(PRIMARY_KEY).in(refIds));

		Query<Lea> q = getSession().createQuery(select);
		List<Lea> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public Lea findByRefId(String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));

		Query<Lea> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public Lea findByLocalId(String localId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(from.get(LOCAL_ID_KEY), localId));

		Query<Lea> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public Lea findBySchoolRefId(String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>fetch("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(schools.get("schoolRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Lea> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public void save(Lea instance) {
		super.persist(instance);
	}

	@Override
	public void update(Lea instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(Lea instance) {
		super.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Lea instance = (Lea) criteria.uniqueResult();
		super.delete(instance);
	}

	@Override
	public Long count(){
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Lea.class)));
		return getSession().createQuery(countQuery).getSingleResult();
	}
}
