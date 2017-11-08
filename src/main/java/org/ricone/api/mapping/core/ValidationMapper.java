package org.ricone.api.mapping.core;

import org.ricone.api.model.core.School;
import org.springframework.stereotype.Component;

@Component("ValidationMapper")
public class ValidationMapper {

    private final String EMAIL_TYPE = "organizational";
    private final String DISTRICT = "district";

    public ValidationMapper() {
    }

    public org.ricone.api.model.validation.School mapSchool(School school, int studentEnrollmentCount, int staffAssignmentCount, int courseCount, int rosterCount) {
        org.ricone.api.model.validation.School instance = new org.ricone.api.model.validation.School();
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
