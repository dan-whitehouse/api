package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentDAO
{
    List<Student> findAll(Pageable pageRequest) throws Exception;

    List<Student> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception;

    List<Student> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception;

    List<Student> findAllByRosterRefId(Pageable pageRequest, String refId) throws Exception;

    List<Student> findAllByStaffRefId(Pageable pageRequest, String refId) throws Exception;

    List<Student> findAllByContactRefId(Pageable pageRequest, String refId) throws Exception;

    Student findByRefId(String refId) throws Exception;

    Student findByLocalId(String localId) throws Exception;

    Student findByStateId(String id) throws Exception;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);

    void deleteByRefId(String refId);



}
