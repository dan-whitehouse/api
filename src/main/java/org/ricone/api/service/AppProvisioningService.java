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
import java.util.*;

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
		Lea l = leaDAO.findBySchoolRefId(metaData, refId);
		Optional<District> district = metaData.getApp().getDistricts().stream().filter(d -> d.getId().equalsIgnoreCase(l.getLeaId())).findFirst();

		if(district.isPresent()){
			if(CollectionUtils.isNotEmpty(district.get().getKv().entrySet())) {
				List<Staff> staffs = staffDAO.findAllBySchoolRefId(metaData, refId);
				staffs.forEach(t -> {
					StaffIdentifier ti = new StaffIdentifier();
					ti.setIdentificationSystemCode(LOGIN_ID);
					ti.setStaff(t);
					ti.setStaffId(generator.getUsername(district.get().getKv(), t, null));
					ti.setStaffIdentifierRefId(UUID.randomUUID().toString());
					t.getStaffIdentifiers().add(ti);
					staffDAO.update(t);
				});
				return userPasswordDAO.provisionStaffsBySchool(metaData, district.get().getKv(), staffs);
			}
		}
		throw new ForbiddenException("The district associated to this school has not been configured for account provisioning");
	}

	@Override
	public boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.deleteStaffsBySchool(metaData, refId);
	}

	@Override
	public List<UserPassword> findStaffsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.findStaffsBySchool(metaData, refId);
	}

	@Override
	public boolean provisionStudentsBySchool(MetaData metaData, String refId) throws Exception {
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
				return userPasswordDAO.provisionStudentsBySchool(metaData, district.get().getKv(), students);
			}
		}
		throw new ForbiddenException("The district associated to this school has not been configured for account provisioning");
	}

	@Override
	public boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {
		return userPasswordDAO.deleteStudentsBySchool(metaData, refId);
	}

	@Override
	public List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception {
		Set<String> refIds = new HashSet<>();
		List<UserPassword> userPasswords = userPasswordDAO.findStudentsBySchool(metaData, refId);
		userPasswords.forEach(userPassword -> {
			refIds.add(userPassword.getEntityRefId());
		});

		List<Student> students = studentDAO.findByRefIds(metaData, refIds);
		userPasswords.forEach(userPassword -> {
			students.forEach(student -> {
				if(student.getStudentRefId().equalsIgnoreCase(userPassword.getEntityRefId())){
					userPassword.setStudent(student);
				}
			});
		});
		return userPasswords;
	}
}
