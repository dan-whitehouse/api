package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;

import java.util.List;

public interface IAppProvisioningService {
    boolean provisionStaffsBySchool(MetaData metaData, String refId) throws Exception;

    boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception;

    List<Staff> findStaffsBySchool(MetaData metaData, String refId) throws Exception;

    boolean provisionStudentsBySchool(MetaData metaData, String refId) throws Exception;

    boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception;

    List<Student> findStudentsBySchool(MetaData metaData, String refId) throws Exception;
}
