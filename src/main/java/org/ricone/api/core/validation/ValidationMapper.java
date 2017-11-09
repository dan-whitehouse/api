package org.ricone.api.core.validation;

import org.ricone.api.core.dao.School;
import org.springframework.stereotype.Component;

@Component("ValidationMapper")
public class ValidationMapper {

    private final String EMAIL_TYPE = "organizational";
    private final String DISTRICT = "district";

    public ValidationMapper() {
    }

    public org.ricone.api.core.validation.model.School mapSchool(School school, int studentEnrollmentCount, int staffAssignmentCount, int courseCount, int rosterCount) {
        org.ricone.api.core.validation.model.School instance = new org.ricone.api.core.validation.model.School();
        instance.setRefId(school.getSchoolRefId());
        instance.setName(school.getSchoolName());
        instance.setStudentEnrollments(studentEnrollmentCount);
        instance.setStaffAssignments(staffAssignmentCount);
        instance.setCourses(courseCount);
        instance.setCourseSections(rosterCount);
        return instance;
    }

    public String getPercentage(int total, int found) {
        float percent = (found * 100.0f) / total;
        return String.format("%.02f", percent) + "%";
    }
}
