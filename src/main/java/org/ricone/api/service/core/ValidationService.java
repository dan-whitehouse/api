package org.ricone.api.service.core;

import org.apache.commons.collections.CollectionUtils;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.core.ValidationDAO;
import org.ricone.api.dao.xPress.*;
import org.ricone.api.mapping.core.ValidationMapper;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
import org.ricone.api.model.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("validationService")
@Transactional
public class ValidationService implements IValidationService
{
	@Autowired
	private ValidationDAO dao;

	@Autowired
	private LeaDAO leaDAO;

	@Autowired
	private SchoolDAO schoolDAO;

	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private CourseDAO courseDAO;

	@Autowired
	private RosterDAO rosterDAO;

	@Autowired
	private ValidationMapper mapper;

	@Override
	public ValidationResponse findAll(MetaData metaData) throws Exception {
		List<Lea> leas = leaDAO.findAll(metaData);

		Districts districts = new Districts();
		leas.forEach(lea -> {

			//Set Student & Staff Data
			Students students = getStudents(lea.getLeaRefId());
			Staff staff = getStaffs(lea.getLeaRefId());

			//Set District
			District district = new District();
			district.setRefId(lea.getLeaRefId());
			district.setName(lea.getLeaName());
			district.setStudents(students);
			district.setStaff(staff);

			try {
				List<School> schools = schoolDAO.findAllByLeaRefId(metaData, lea.getLeaRefId());
				if(CollectionUtils.isNotEmpty(schools)) {
					Schools v_schools = new Schools();
					schools.forEach(school -> {
						final int staffAssignmentCount = dao.countStaffPrimaryAssignmentsBySchoolRefId(school.getSchoolRefId());
						final int studentEnrollmentCount = dao.countStudentPrimaryEnrollmentsBySchoolRefId(school.getSchoolRefId());
						final int courseCount = dao.countCoursesBySchoolRefId(school.getSchoolRefId());
						final int rosterCount = dao.countRostersBySchoolRefId(school.getSchoolRefId());

						org.ricone.api.model.validation.School v_school = mapper.mapSchool(school, studentEnrollmentCount, staffAssignmentCount, courseCount, rosterCount);

						//Add School to School List
						v_schools.getSchool().add(v_school);
					});
					district.setSchools(v_schools);
				}
			}
			catch (Exception e) {/*idk why a try catch is needed, shouldn't the method throws exception handle this?*/}
			districts.getDistrict().add(district);
		});

		ValidationResponse validation = new ValidationResponse();
		validation.setDistricts(districts);
		return validation;
	}

	@Override
	public ValidationResponse findAllByLea(MetaData metaData, String refId) throws Exception {
		Lea lea = leaDAO.findByRefId(metaData, refId);
		List<School> schools = schoolDAO.findAllByLeaRefId(metaData, refId);

		//Set Student & Staff Data
		Students students = getStudents(lea.getLeaRefId());
		Staff staff = getStaffs(lea.getLeaRefId());

		//Set Validation Response
		ValidationResponse validation = new ValidationResponse();
		Districts districts = new Districts();
		District district = new District();
		district.setRefId(lea.getLeaRefId());
		district.setName(lea.getLeaName());
		district.setStudents(students);
		district.setStaff(staff);

		if(CollectionUtils.isNotEmpty(schools)) {
			Schools v_schools = new Schools();
			for(School sch : schools)
			{
				final int staffAssignmentCount = dao.countStaffPrimaryAssignmentsBySchoolRefId(sch.getSchoolRefId());
				final int studentEnrollmentCount = dao.countStudentPrimaryEnrollmentsBySchoolRefId(sch.getSchoolRefId());
				final int courseCount = dao.countCoursesBySchoolRefId(sch.getSchoolRefId());
				final int rosterCount = dao.countRostersBySchoolRefId(sch.getSchoolRefId());

				org.ricone.api.model.validation.School v_school = mapper.mapSchool(sch, studentEnrollmentCount, staffAssignmentCount, courseCount, rosterCount);

				//Add School to School List
				v_schools.getSchool().add(v_school);
			}
			district.setSchools(v_schools);
		}

		districts.getDistrict().add(district);
		validation.setDistricts(districts);
		return validation;
	}

	private Students getStudents(String leaRefId) {
		int studentCount = dao.countStudentsByLeaRefId(leaRefId);
		int studentEmailCount = dao.countStudentPrimaryEmailsByLeaRefId(leaRefId);
		String studentEmailPercentage = mapper.getPercentage(studentCount, studentEmailCount);
		int studentLocalIdCount = dao.countStudentLocalIdsByLeaRefId(leaRefId);
		String studentLocalIdPercentage = mapper.getPercentage(studentCount, studentLocalIdCount);
		Email studentEmail = new Email(studentEmailCount, studentEmailPercentage);
		LocalId studentLocalId = new LocalId(studentLocalIdCount, studentLocalIdPercentage);
		return new Students(studentCount, studentEmail, studentLocalId);
	}

	private Staff getStaffs(String leaRefId) {
		int staffCount = dao.countStaffByLeaRefId(leaRefId);
		int staffEmailCount = dao.countStaffPrimaryEmailsByLeaRefId(leaRefId);
		String staffEmailPercentage = mapper.getPercentage(staffCount, staffEmailCount);
		int staffLocalIdCount = dao.countStaffLocalIdsByLeaRefId(leaRefId);
		String staffLocalIdPercentage = mapper.getPercentage(staffCount, staffLocalIdCount);
		Email staffEmail = new Email(staffEmailCount, staffEmailPercentage);
		LocalId staffLocalId = new LocalId(staffLocalIdCount, staffLocalIdPercentage);
		return new Staff(staffCount, staffEmail, staffLocalId);
	}

}
