package org.ricone.api.dao;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.core.UserPassword;

import java.util.HashMap;
import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 10/29/2017.
 */
public interface IUserPasswordDAO {
    boolean provisionStaffsBySchool(MetaData metaData, HashMap<String, String> kv, List<Staff> staffs) throws Exception;

    boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception;

    List<UserPassword> findStaffsBySchool(MetaData metaData, String refId) throws Exception;

    boolean provisionStudentsBySchool(MetaData metaData, HashMap<String, String> kv, List<Student> students)throws Exception;

    boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception;

    List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception;

    void save(UserPassword instance);

    void update(UserPassword instance);

    void delete(UserPassword instance);
}
