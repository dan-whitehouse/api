package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.UserPassword;

import java.util.List;

public interface IAppProvisioningService {
    boolean provisionStaffsBySchool(MetaData metaData, String refId) throws Exception;

    boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception;

    List<UserPassword> findStaffsBySchool(MetaData metaData, String refId) throws Exception;

    boolean provisionStudentsBySchool(MetaData metaData, String refId) throws Exception;

    boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception;

    List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception;
}
