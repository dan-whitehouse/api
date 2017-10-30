package org.ricone.api.dao;

import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.core.UserPassword;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 10/29/2017.
 */
public interface IUserPasswordDAO {
    boolean provisionStaffsBySchool(List<Staff> staffs) throws Exception;

    boolean deleteStaffsBySchool(Pageable pageRequest, String refId) throws Exception;

    List<Staff> findStaffsBySchool(Pageable pageRequest, String refId) throws Exception;

    boolean provisionStudentsBySchool(Pageable pageRequest, String refId)throws Exception;

    boolean deleteStudentsBySchool(Pageable pageRequest, String refId) throws Exception;

    List<Student> findStudentsBySchool(Pageable pageRequest, String refId) throws Exception;

    void save(UserPassword instance);

    void update(UserPassword instance);

    void delete(UserPassword instance);
}
