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

import javax.persistence.criteria.*;
import java.util.List;

@Repository("ContactDAO")
@SuppressWarnings("unchecked")
public class ContactDAO extends AbstractDAO<Integer, StudentContact> implements IContactDAO
{
	private final String PRIMARY_KEY = "studentContactRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<StudentContact> findAll(Pageable pageRequest) throws Exception {

		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContact> select = cb.createQuery(StudentContact.class);
		final Root<StudentContact> from = select.from(StudentContact.class);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}

		List<StudentContact> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentContactAddresses());
			Hibernate.initialize(o.getStudentContactEmails());
			Hibernate.initialize(o.getStudentContactIdentifiers());
			Hibernate.initialize(o.getStudentContactOtherNames());
			Hibernate.initialize(o.getStudentContactTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());
			o.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContact> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContact> select = cb.createQuery(StudentContact.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>fetch("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>fetch("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(lea.get("leaRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}

		List<StudentContact> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentContactAddresses());
			Hibernate.initialize(o.getStudentContactEmails());
			Hibernate.initialize(o.getStudentContactIdentifiers());
			Hibernate.initialize(o.getStudentContactOtherNames());
			Hibernate.initialize(o.getStudentContactTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());
			o.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContact> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContact> select = cb.createQuery(StudentContact.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>fetch("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>fetch("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(school.get("schoolRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}

		List<StudentContact> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentContactAddresses());
			Hibernate.initialize(o.getStudentContactEmails());
			Hibernate.initialize(o.getStudentContactIdentifiers());
			Hibernate.initialize(o.getStudentContactOtherNames());
			Hibernate.initialize(o.getStudentContactTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());
			o.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContact> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContact> select = cb.createQuery(StudentContact.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>fetch("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>fetch("student", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(student.get("studentRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}

		List<StudentContact> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentContactAddresses());
			Hibernate.initialize(o.getStudentContactEmails());
			Hibernate.initialize(o.getStudentContactIdentifiers());
			Hibernate.initialize(o.getStudentContactOtherNames());
			Hibernate.initialize(o.getStudentContactTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());
			o.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public StudentContact findByRefId(String refId) throws Exception
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContact> select = cb.createQuery(StudentContact.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>fetch("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>fetch("student", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));

		Query<StudentContact> q = getSession().createQuery(select);
		StudentContact instance = q.getSingleResult();

		Hibernate.initialize(instance.getStudentContactAddresses());
		Hibernate.initialize(instance.getStudentContactEmails());
		Hibernate.initialize(instance.getStudentContactIdentifiers());
		Hibernate.initialize(instance.getStudentContactOtherNames());
		Hibernate.initialize(instance.getStudentContactTelephones());

		Hibernate.initialize(instance.getStudentContactRelationships());
		instance.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));

		return instance;
	}


	@Override
	public void save(StudentContact instance) {
		persist(instance);
	}

	@Override
	public void update(StudentContact instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(StudentContact instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		StudentContact instance = (StudentContact)criteria.uniqueResult();
		delete(instance);
	}
}
