package org.ricone.api.xPress.request.xContact;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("ContactDAO")
@SuppressWarnings("unchecked")
public class ContactDAO extends AbstractDAO<Integer, StudentContact> implements IContactDAO
{
	private final String PRIMARY_KEY = "studentContactRefId";

	@Override
	public List<StudentContactWrapper> findAll(MetaData metaData) throws Exception {

		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>join("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get("leaId"), from));
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContactWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentContactWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());

			Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>join("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContactWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentContactWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());

			Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>join("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get("leaId"), from));
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

		Query<StudentContactWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentContactWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());

			Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>join("student", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(student.get("studentRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentContactWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentContactWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());

			Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>join("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentContactWrapper> q = getSession().createQuery(select);

		List<StudentContactWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());

			Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public StudentContactWrapper findByRefId(MetaData metaData, String refId) throws Exception
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = (Join<StudentContactRelationship, Student>)studentContactRelationships.<StudentContactRelationship, Student>join("student", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentContactWrapper> q = getSession().createQuery(select);

		StudentContactWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStudentContact().getStudentContactAddresses());
		Hibernate.initialize(instance.getStudentContact().getStudentContactEmails());
		Hibernate.initialize(instance.getStudentContact().getStudentContactIdentifiers());
		Hibernate.initialize(instance.getStudentContact().getStudentContactOtherNames());
		Hibernate.initialize(instance.getStudentContact().getStudentContactTelephones());
		Hibernate.initialize(instance.getStudentContact().getStudentContactRelationships());
		instance.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
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
