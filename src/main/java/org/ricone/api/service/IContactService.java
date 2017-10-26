package org.ricone.api.service;

import org.ricone.api.model.core.StudentContact;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContactService
{
    List<StudentContact> findAll(Pageable paging) throws Exception;

    List<StudentContact> findAllByLea(Pageable paging, String refId) throws Exception;

    List<StudentContact> findAllBySchool(Pageable paging, String refId) throws Exception;

    List<StudentContact> findAllByStudent(Pageable paging, String refId) throws Exception;

    StudentContact findByRefId(String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);

    void deleteByRefId(String refId);
}
