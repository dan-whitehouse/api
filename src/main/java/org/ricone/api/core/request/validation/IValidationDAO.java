package org.ricone.api.core.request.validation;

public interface IValidationDAO {
    int countStaffByLeaRefId(String refId);

    int countStaffPrimaryAssignmentsBySchoolRefId(String refId);

    int countStaffPrimaryEmailsByLeaRefId(String refId);

    int countStaffLocalIdsByLeaRefId(String refId);

    int countStudentsByLeaRefId(String refId);

    int countStudentPrimaryEnrollmentsBySchoolRefId(String refId);

    int countStudentPrimaryEmailsByLeaRefId(String refId);

    int countStudentLocalIdsByLeaRefId(String refId);


    int countCoursesBySchoolRefId(String refId);

    int countRostersBySchoolRefId(String refId);
}
