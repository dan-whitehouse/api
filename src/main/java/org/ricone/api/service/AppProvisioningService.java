package org.ricone.api.service;

import org.ricone.api.component.config.ConfigService;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.LeaDAO;
import org.ricone.api.dao.StaffDAO;
import org.ricone.api.dao.StudentDAO;
import org.ricone.api.dao.UserPasswordDAO;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.StaffIdentifier;
import org.ricone.api.model.core.Student;
import org.ricone.api.util.UserPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("appProvisioning")
@Transactional
public class AppProvisioningService implements IAppProvisioningService
{
	private final String LOGIN_ID = "LoginId";

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
	public boolean provisionStaffsBySchool(MetaData metaData, String refId) throws Exception{
		List<Staff> staffs = staffDAO.findAllBySchoolRefId(metaData, refId);

		Lea l = leaDAO.findBySchoolRefId(metaData, refId);

		HashMap<String, String> kv = ConfigService.getInstance().getDistrictAPIKV(l.getLeaRefId());
		staffs.forEach(t -> {
			StaffIdentifier ti = new StaffIdentifier();
			ti.setIdentificationSystemCode(LOGIN_ID);
			ti.setStaff(t);
			ti.setStaffId(generator.getUsername(kv, t, null));
			ti.setStaffIdentifierRefId(UUID.randomUUID().toString());
			t.getStaffIdentifiers().add(ti);
			staffDAO.update(t);
		});

		return userPasswordDAO.provisionStaffsBySchool(staffs);
	}

	@Override
	public boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.deleteStaffsBySchool(metaData, refId);
	}

	@Override
	public List<Staff> findStaffsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.findStaffsBySchool(metaData, refId);
	}

	@Override
	public boolean provisionStudentsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.provisionStudentsBySchool(metaData, refId);
	}

	@Override
	public boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.deleteStudentsBySchool(metaData, refId);
	}

	@Override
	public List<Student> findStudentsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.findStudentsBySchool(metaData, refId);
	}
}
