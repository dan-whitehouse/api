package org.ricone.api.service;

import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAppProvisioningService {
    boolean provisionStaffsBySchool(Pageable paging, String refId) throws Exception;

    boolean deleteStaffsBySchool(Pageable paging, String refId) throws Exception;

    List<Staff> findStaffsBySchool(Pageable paging, String refId) throws Exception;

    boolean provisionStudentsBySchool(Pageable paging, String refId) throws Exception;

    boolean deleteStudentsBySchool(Pageable paging, String refId) throws Exception;

    List<Student> findStudentsBySchool(Pageable paging, String refId) throws Exception;
}
