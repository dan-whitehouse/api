package org.ricone.api.service;

import org.apache.commons.collections.CollectionUtils;
import org.ricone.api.component.config.model.District;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.LeaDAO;
import org.ricone.api.dao.StaffDAO;
import org.ricone.api.dao.StudentDAO;
import org.ricone.api.dao.UserPasswordDAO;
import org.ricone.api.exception.ForbiddenException;
import org.ricone.api.model.core.*;
import org.ricone.api.util.UserPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("appProvisioning")
@Transactional
public class AppProvisioningService implements IAppProvisioningService
{
	private final String LOGIN_ID = "LoginId";
	private final String FORBIDDEN_EXCEPTION_MESSAGE = "The district associated to this school has not been configured for account provisioning";

	@Autowired
	UserPasswordDAO userPasswordDAO;

	@Autowired
	LeaDAO leaDAO;

	@Autowired
	StaffDAO staffDAO;

	@Autowired
	StudentDAO studentDAO;

	@Autowired
	UserPasswordGenerator generator;

	@Override
	public void provisionStaffsBySchool(MetaData metaData, String refId) throws Exception{
		Lea l = leaDAO.findBySchoolRefId(metaData, refId);
		Optional<District> district = metaData.getApp().getDistricts().stream().filter(d -> d.getId().equalsIgnoreCase(l.getLeaId())).findFirst();

		if(district.isPresent()){
			if(CollectionUtils.isNotEmpty(district.get().getKv().entrySet())) {
				List<Staff> staffs = staffDAO.findAllBySchoolRefId(metaData, refId);
				staffs.forEach(t -> {
					StaffIdentifier ti = new StaffIdentifier();
					ti.setStaffIdentifierRefId(UUID.randomUUID().toString());
					ti.setStaff(t);
					ti.setStaffId(generator.getUsername(district.get().getKv(), t, null));
					ti.setIdentificationSystemCode(LOGIN_ID);
					t.getStaffIdentifiers().add(ti);
					staffDAO.update(t);
				});
				userPasswordDAO.provisionStaffsBySchool(metaData, district.get().getKv(), staffs);
			}
		}
		throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
	}

	@Override
	public void provisionStudentsBySchool(MetaData metaData, String refId) throws Exception {
		Lea l = leaDAO.findBySchoolRefId(metaData, refId);
		Optional<District> district = metaData.getApp().getDistricts().stream().filter(d -> d.getId().equalsIgnoreCase(l.getLeaId())).findFirst();

		if(district.isPresent()){
			if(CollectionUtils.isNotEmpty(district.get().getKv().entrySet())) {
				List<Student> students = studentDAO.findAllBySchoolRefId(metaData, refId);
				students.forEach(s -> {
					StudentIdentifier si = new StudentIdentifier();
					si.setIdentificationSystemCode(LOGIN_ID);
					si.setStudent(s);
					si.setStudentId(generator.getUsername(district.get().getKv(), s, null));
					si.setStudentIdentifierRefId(UUID.randomUUID().toString());
					s.getStudentIdentifiers().add(si);
					studentDAO.update(s);
				});
				userPasswordDAO.provisionStudentsBySchool(metaData, district.get().getKv(), students);
			}
		}
		throw new ForbiddenException(FORBIDDEN_EXCEPTION_MESSAGE);
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
		userPasswordDAO.deleteStaffsBySchool(metaData, refId);
		//userPasswordDAO.deleteStaffsLoginIdBySchool(metaData, refId);
	}

	@Override
	public void deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {
		userPasswordDAO.deleteStudentsBySchool(metaData, refId);
		//userPasswordDAO.deleteStudentsLoginIdBySchool(metaData, refId);
	}
}
