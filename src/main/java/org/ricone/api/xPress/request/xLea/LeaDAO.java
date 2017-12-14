package org.ricone.api.xPress.request.xLea;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
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

	@Override
	public List<LeaWrapper> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where(from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(schools.get("schoolRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>join("schoolCalendars", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join("courses", JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(courses.get("courseRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join("courses", JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join("courseSections", JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(courseSections.get("courseSectionRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>join("staffAssignments", JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = (Join<StaffAssignment, Staff>) staffAssignments.<StaffAssignment, Staff>join("staff", JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(staff.get("staffRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		
		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join("studentEnrollments", JoinType.INNER);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>join("student", JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(student.get("studentRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		
		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByContactRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join("studentEnrollments", JoinType.INNER);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>join("student", JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = (Join<StudentContactRelationship, StudentContact>) studentContactRelationships.<StudentContactRelationship, StudentContact>join("studentContact", JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(contact.get("studentContactRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		
		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<LeaWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<LeaWrapper> q = getSession().createQuery(select);
		List<LeaWrapper> instance = q.getResultList();
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public LeaWrapper findByRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(PRIMARY_KEY), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<LeaWrapper> q = getSession().createQuery(select);
		LeaWrapper instance = q.getSingleResult();
		instance.getLea().getLeaTelephones().forEach(Hibernate::initialize);
		instance.getLea().getSchools().forEach(Hibernate::initialize);
		return instance;
	}

	@Override
	public LeaWrapper findByLocalId(MetaData metaData, String localId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(LOCAL_ID_KEY), localId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<LeaWrapper> q = getSession().createQuery(select);
		LeaWrapper instance = q.getSingleResult();
		instance.getLea().getLeaTelephones().forEach(Hibernate::initialize);
		instance.getLea().getSchools().forEach(Hibernate::initialize);
		return instance;
	}

	@Override
	public LeaWrapper findBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join("leaTelephones", JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join("schools", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(schools.get("schoolRefId"), refId),
				from.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<LeaWrapper> q = getSession().createQuery(select);
		LeaWrapper instance = q.getSingleResult();
		instance.getLea().getLeaTelephones().forEach(Hibernate::initialize);
		instance.getLea().getSchools().forEach(Hibernate::initialize);
		return instance;
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
	public Long count(){
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		countQuery.select(cb.count(countQuery.from(Lea.class)));
		return getSession().createQuery(countQuery).getSingleResult();
	}
}
