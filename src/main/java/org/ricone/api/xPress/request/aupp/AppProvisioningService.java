package org.ricone.api.xPress.request.aupp;

import org.apache.commons.collections.CollectionUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.xPress.request.xStaff.IStaffDAO;
import org.ricone.api.xPress.request.xStudent.IStudentDAO;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.ConflictException;
import org.ricone.error.exception.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("appProvisioning")
@Transactional
public class AppProvisioningService implements IAppProvisioningService
{
	private final String LOGIN_ID = "LoginId";
	private final String FORBIDDEN_EXCEPTION_MESSAGE = "The district associated to this school has not been configured for account provisioning";
	private final String CONFLICT_EXCEPTION_MESSAGE = "All user accounts have already been created. To create new accounts, you must first delete existing ones";

	@Autowired
	private IUserPasswordDAO userPasswordDAO;

	@Autowired
	private IStaffDAO staffDAO;

	@Autowired
	private IStaffIdentifierDAO staffIdentifierDAO;

	@Autowired
	private IStudentDAO studentDAO;

	@Autowired
	private IStudentIdentifierDAO studentIdentifierDAO;

	@Autowired
	private UserPasswordGenerator generator;

	@Override
	public void provisionStaffsBySchool(MetaData metaData, String refId) throws Exception{
		if(metaData.getApp().getDistrictKVsBySchool(refId) != null) {
			if(CollectionUtils.isNotEmpty(metaData.getApp().getDistrictKVsBySchool(refId).entrySet())) {
				List<Staff> staffs = staffDAO.findAllBySchoolRefId(metaData, refId);
				List<Staff> staffsWithIdentifiersCreated = new ArrayList<>();
				int identifiersCreated = 0;
				for (Staff t : staffs) {
					Optional<StaffIdentifier> identifier = t.getStaffIdentifiers().stream().filter(si -> si.getIdentificationSystemCode().equalsIgnoreCase(LOGIN_ID)).findFirst();
					if (!identifier.isPresent()) {
						StaffIdentifier ti = new StaffIdentifier();
						ti.setStaffIdentifierRefId(UUID.randomUUID().toString());
						ti.setStaff(t);

						String username = generator.getUsername(metaData.getApp().getDistrictKVsBySchool(refId), t, null);
						ti.setStaffId(username);

						// Counts how many LoginIds with the same name exist in model. If it's a new LoginId then the count will be 0.
						// If multiple LoginId's start with the same name and/or ends in a number, we count them and subtract 0.
						if(AppProvisioningUtil.doesUsernameRequireIncrement(EntityType.STAFF, metaData.getApp().getDistrictKVsBySchool(refId))) {
							int increment = staffIdentifierDAO.countLoginIdsBySchoolRefId(refId, t.getStaffRefId(), ti.getStaffId());
							if(increment > 0){
								ti.setStaffId(generator.getUsername(metaData.getApp().getDistrictKVsBySchool(refId), t, increment-1));
							}
						}

						ti.setIdentificationSystemCode(LOGIN_ID);
						t.getStaffIdentifiers().add(ti);
						staffDAO.update(t);
						identifiersCreated++;
						staffsWithIdentifiersCreated.add(t);
					}
				}
				if(staffs.size() > 0 && identifiersCreated > 0){
					//For every staff an identifier was created for, create a user password
					userPasswordDAO.provisionStaffsBySchool(metaData, metaData.getApp().getDistrictKVsBySchool(refId), staffsWithIdentifiersCreated);
				}
				else {
					throw new ConflictException(CONFLICT_EXCEPTION_MESSAGE);
				}
			}
		}
		else throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public void provisionStudentsBySchool(MetaData metaData, String refId) throws Exception {
		if(metaData.getApp().getDistrictKVsBySchool(refId) != null) {
			if(CollectionUtils.isNotEmpty(metaData.getApp().getDistrictKVsBySchool(refId).entrySet())) {
				List<Student> students = studentDAO.findAllBySchoolRefId(metaData, refId);
				List<Student> studentsWithIdentifiersCreated = new ArrayList<>();
				int identifiersCreated = 0;
				for (Student s : students) {
					Optional<StudentIdentifier> identifier = s.getStudentIdentifiers().stream().filter(si -> si.getIdentificationSystemCode().equalsIgnoreCase(LOGIN_ID)).findFirst();
					if (!identifier.isPresent()) {
						StudentIdentifier ti = new StudentIdentifier();
						ti.setStudentIdentifierRefId(UUID.randomUUID().toString());
						ti.setStudent(s);

						String username = generator.getUsername(metaData.getApp().getDistrictKVsBySchool(refId), s, null);
						ti.setStudentId(username);

						// Counts how many LoginIds with the same name exist in model. If it's a new LoginId then the count will be 0.
						// If multiple LoginId's start with the same name, we count them and subtract 0.
						if(AppProvisioningUtil.doesUsernameRequireIncrement(EntityType.STUDENT, metaData.getApp().getDistrictKVsBySchool(refId))) {
							int increment = studentIdentifierDAO.countLoginIdsBySchoolRefId(refId, s.getStudentRefId(), ti.getStudentId());
							if(increment > 0){
								ti.setStudentId(generator.getUsername(metaData.getApp().getDistrictKVsBySchool(refId), s, increment-1));
							}
						}

						ti.setIdentificationSystemCode(LOGIN_ID);
						s.getStudentIdentifiers().add(ti);
						studentDAO.update(s);
						identifiersCreated++;
						studentsWithIdentifiersCreated.add(s);
					}
				}
				if(students.size() > 0 && identifiersCreated > 0){
					//For every student an identifier was created for, create a user password
					userPasswordDAO.provisionStudentsBySchool(metaData, metaData.getApp().getDistrictKVsBySchool(refId), studentsWithIdentifiersCreated);
				}
				else {
					throw new ConflictException(CONFLICT_EXCEPTION_MESSAGE);
				}
			}
		}
		else throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public List<UserPassword> findStaffsBySchool(MetaData metaData, String refId) throws Exception {
		if(metaData.getApp().getDistrictKVsBySchool(refId) != null) {
			userPasswordDAO.updateStaffsLastRetrievedBySchool(metaData, refId);
			List<UserPassword> userPasswords = userPasswordDAO.findStaffsBySchool(metaData, refId);
			return userPasswords;
		}
		throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception {
		if(metaData.getApp().getDistrictKVsBySchool(refId) != null) {
			userPasswordDAO.updateStudentsLastRetrievedBySchool(metaData, refId);
			List<UserPassword> userPasswords = userPasswordDAO.findStudentsBySchool(metaData, refId);
			return userPasswords;
		}
		throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public void deleteStaffsBySchool(MetaData metaData, String refId) throws Exception {
		if(metaData.getApp().getDistrictKVsBySchool(refId) != null) {
			userPasswordDAO.deleteStaffsBySchool(metaData, refId);
		}
		else throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public void deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {
		if(metaData.getApp().getDistrictKVsBySchool(refId) != null) {
			userPasswordDAO.deleteStudentsBySchool(metaData, refId);
		}
		else throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public void deleteStaffsLoginIdBySchool(MetaData metaData, String refId) throws Exception {
		List<Staff> staffs = staffDAO.findAllBySchoolRefId(metaData, refId);
		for (Staff staff : staffs) {
			Optional<StaffIdentifier> identifier = staff.getStaffIdentifiers().stream().filter(si -> si.getIdentificationSystemCode().equalsIgnoreCase(LOGIN_ID)).findFirst();
			if(identifier.isPresent()) {
				staff.getStaffIdentifiers().remove(identifier.get());
				staffIdentifierDAO.delete(identifier.get());
			}
		}
	}

	@Override
	public void deleteStudentsLoginIdBySchool(MetaData metaData, String refId) throws Exception {
		List<Student> students = studentDAO.findAllBySchoolRefId(metaData, refId);
		for (Student student : students) {
			Optional<StudentIdentifier> identifier = student.getStudentIdentifiers().stream().filter(si -> si.getIdentificationSystemCode().equalsIgnoreCase(LOGIN_ID)).findFirst();
			if(identifier.isPresent()) {
				student.getStudentIdentifiers().remove(identifier.get());
				studentIdentifierDAO.delete(identifier.get());
			}
		}
	}
}
