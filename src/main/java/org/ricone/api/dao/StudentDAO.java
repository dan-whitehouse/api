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

@Repository("StudentDAO")
public class StudentDAO extends AbstractDAO<Integer, Student> implements IStudentDAO
{
	private final String PRIMARY_KEY = "studentRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Student> findAll(Pageable pageRequest) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentAcademicRecord> studentAcademicRecords = (SetJoin<Student, StudentAcademicRecord>) from.<Student, StudentAcademicRecord>fetch("studentAcademicRecords", JoinType.LEFT);
		final SetJoin<Student, StudentAddress> studentAddresses = (SetJoin<Student, StudentAddress>) from.<Student, StudentAddress>fetch("studentAddresses", JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>fetch("studentIdentifiers", JoinType.LEFT);
		final SetJoin<Student, StudentLanguage> studentLanguages = (SetJoin<Student, StudentLanguage>) from.<Student, StudentLanguage>fetch("studentLanguages", JoinType.LEFT);
		final SetJoin<Student, StudentOtherName> studentOtherNames = (SetJoin<Student, StudentOtherName>) from.<Student, StudentOtherName>fetch("studentOtherNames", JoinType.LEFT);
		final SetJoin<Student, StudentRace> studentRaces = (SetJoin<Student, StudentRace>) from.<Student, StudentRace>fetch("studentRaces", JoinType.LEFT);
		final SetJoin<Student, StudentTelephone> studentTelephones = (SetJoin<Student, StudentTelephone>) from.<Student, StudentTelephone>fetch("studentTelephones", JoinType.LEFT);

		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>fetch("studentContactRelationships", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, Staff> counselor = studentEnrollments.join("counselor", JoinType.LEFT);
		final Join<StudentEnrollment, Staff> teacher = studentEnrollments.join("teacher", JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>fetch("entryExitCodes", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public Student findByRefId(String refId) throws Exception
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Student instance = (Student)criteria.uniqueResult();
		if(instance != null)
		{
			Hibernate.initialize(instance.getStudentAcademicRecords());
			Hibernate.initialize(instance.getStudentAddresses());
			Hibernate.initialize(instance.getStudentEmails());
			Hibernate.initialize(instance.getStudentIdentifiers());
			Hibernate.initialize(instance.getStudentLanguages());
			Hibernate.initialize(instance.getStudentOtherNames());
			Hibernate.initialize(instance.getStudentRaces());
			Hibernate.initialize(instance.getStudentTelephones());

			Hibernate.initialize(instance.getStudentContactRelationships());
			instance.getStudentContactRelationships().forEach(cr -> {
				Hibernate.initialize(cr.getStudentContact());
			});

			Hibernate.initialize(instance.getStudentEnrollments());
			instance.getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
				//Hibernate.initialize(se.getSchool().getLea());
			});
			return instance;
		}
		throw new NotFoundException("No record found with refId: " + refId);
	}

	@Override
	public Student findByLocalId(String localId) throws NotFoundException
	{
		//TODO: This isn't implemented
		return null;
	}

	@Override
	public void save(Student instance) {
		persist(instance);
	}

	@Override
	public void update(Student instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(Student instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Student instance = (Student)criteria.uniqueResult();
		delete(instance);
	}
}
