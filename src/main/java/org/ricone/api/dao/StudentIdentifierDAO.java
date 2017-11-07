package org.ricone.api.dao;

import org.ricone.api.model.core.School;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.core.StudentEnrollment;
import org.ricone.api.model.core.StudentIdentifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.regex.Pattern;

@Repository("studentIdentifierDAO")
@SuppressWarnings("unchecked")
public class StudentIdentifierDAO extends AbstractDAO<Integer, StudentIdentifier> implements IStudentIdentifierDAO
{
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final String LOGIN_ID = "LoginId";

	@Override
	public int countLoginIdsBySchoolRefId(String refId, String studentRefId, String logInId) throws Exception {
		//https://stackoverflow.com/questions/24995881/use-regular-expressions-in-jpa-criteriabuilder/24996378#24996378
		//https://stackoverflow.com/questions/32304308/mysql-where-string-ends-with-numbers
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentIdentifier> from = select.from(StudentIdentifier.class);
		final Join<StudentIdentifier, Student> student = (Join<StudentIdentifier, Student>) from.<StudentIdentifier, Student>join("student", JoinType.INNER);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.INNER);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>) studentEnrollments.<StudentEnrollment, School>join("school", JoinType.INNER);

		Pattern pattern = Pattern.compile("[[:digit:]]$");
		Expression<String> expression = cb.literal(pattern.pattern());

		select.select(cb.countDistinct(from));
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				cb.equal(from.get(IDENTIFICATION_SYSTEM_CODE), LOGIN_ID),
				cb.and //ie: dan1, dan2
				(
					cb.like(from.get("studentId"), logInId+"%"),
					cb.or(
						cb.equal(from.get("studentId"), logInId),
						cb.function("regexp", Integer.class, from.get("studentId"), expression).isNotNull()
					)
				)
			)
		);
		return getSession().createQuery(select).getSingleResult().intValue();
	}

	@Override
	public void save(StudentIdentifier instance) {
		super.persist(instance);
	}

	@Override
	public void update(StudentIdentifier instance){
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(StudentIdentifier instance){
		super.delete(instance);
	}
}
