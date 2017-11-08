package org.ricone.api.dao.xPress;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.AbstractDAO;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.model.core.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("ContactDAO")
@SuppressWarnings("unchecked")
public class ContactDAO extends AbstractDAO<Integer, StudentContact> implements IContactDAO
{
	private final String PRIMARY_KEY = "studentContactRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<StudentContact> findAll(MetaData metaData) throws Exception {

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
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
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
	public List<StudentContact> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
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
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
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
	public List<StudentContact> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
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

		Query<StudentContact> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
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
	public List<StudentContact> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
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
		select.where
		(
			cb.and
			(
				cb.equal(student.get("studentRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContact> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
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
	public List<StudentContact> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
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
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentContact> q = getSession().createQuery(select);

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
	public StudentContact findByRefId(MetaData metaData, String refId) throws Exception
	{
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
		select.where
		(
			cb.and
			(
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

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
}
