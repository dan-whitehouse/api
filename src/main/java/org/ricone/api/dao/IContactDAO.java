package org.ricone.api.dao;

import org.ricone.api.model.core.StudentContact;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IContactDAO
{
    List<StudentContact> findAll(Pageable pageRequest) throws Exception;

    List<StudentContact> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception;

    List<StudentContact> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception;

    List<StudentContact> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception;

    List<StudentContact> findByRefIds(Set<String> refIds) throws Exception;

    StudentContact findByRefId(String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);

    void deleteByRefId(String refId);
}
