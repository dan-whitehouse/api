package org.ricone.api.service.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.UserPassword;

import java.util.List;

public interface IAppProvisioningService {

    void provisionStaffsBySchool(MetaData metaData, String refId) throws Exception;

    void provisionStudentsBySchool(MetaData metaData, String refId) throws Exception;

    List<UserPassword> findStaffsBySchool(MetaData metaData, String refId) throws Exception;

    List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception;

    void deleteStaffsBySchool(MetaData metaData, String refId) throws Exception;

    void deleteStudentsBySchool(MetaData metaData, String refId) throws Exception;

    void deleteStaffsLoginIdBySchool(MetaData metaData, String refId) throws Exception;

    void deleteStudentsLoginIdBySchool(MetaData metaData, String refId) throws Exception;
}
