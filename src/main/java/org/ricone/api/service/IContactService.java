package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.StudentContact;

import java.util.List;

public interface IContactService
{
    List<StudentContact> findAll() throws Exception;

    StudentContact findByRefId(String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);

    void deleteByRefId(String refId);
}
