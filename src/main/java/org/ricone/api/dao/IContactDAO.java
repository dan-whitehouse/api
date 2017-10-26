package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.StudentContact;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContactDAO
{
    List<StudentContact> findAll(Pageable pageRequest) throws Exception;

    List<StudentContact> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception;

    List<StudentContact> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception;

    List<StudentContact> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception;

    StudentContact findByRefId(String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);

    void deleteByRefId(String refId);
}
