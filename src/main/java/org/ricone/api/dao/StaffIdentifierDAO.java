package org.ricone.api.dao;

import org.ricone.api.model.core.School;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.StaffAssignment;
import org.ricone.api.model.core.StaffIdentifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.regex.Pattern;

@Repository("staffIdentifierDAO")
@SuppressWarnings("unchecked")
public class StaffIdentifierDAO extends AbstractDAO<Integer, StaffIdentifier> implements IStaffIdentifierDAO
{
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final String LOGIN_ID = "LoginId";
	@Override
	public int countLoginIdsBySchoolRefId(String refId, String staffRefId,  String logInId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StaffIdentifier> from = select.from(StaffIdentifier.class);
		final Join<StaffIdentifier, Staff> staff = (Join<StaffIdentifier, Staff>) from.<StaffIdentifier, Staff>join("staff", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);

		Pattern pattern = Pattern.compile("[[:digit:]]$");
		Expression<String> expression = cb.literal(pattern.pattern());

		//TODO - Query isn't right.
		//https://stackoverflow.com/questions/24995881/use-regular-expressions-in-jpa-criteriabuilder/24996378#24996378
		//https://stackoverflow.com/questions/32304308/mysql-where-string-ends-with-numbers
		select.select(cb.countDistinct(from));
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				cb.equal(from.get(IDENTIFICATION_SYSTEM_CODE), LOGIN_ID),
				cb.and //ie: dan1, dan2
				(
					cb.like(from.get("staffId"), logInId+"%"),
					cb.function("regexp", Integer.class, from.get("staffId"), expression).isNotNull(),
					cb.or(cb.equal(from.get("staffId"), logInId))
				)
			)
		);
		return getSession().createQuery(select).getSingleResult().intValue();
	}

	@Override
	public void save(StaffIdentifier instance) {
		super.persist(instance);
	}

	@Override
	public void update(StaffIdentifier instance){
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(StaffIdentifier instance){
		super.delete(instance);
	}
}
