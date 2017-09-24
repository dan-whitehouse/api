package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.StudentContact;

import java.util.List;

public interface IContactDAO
{
    List<StudentContact> findAll() throws NotFoundException;

    StudentContact findByRefId(String refId) throws NotFoundException;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);

    void deleteByRefId(String refId);
}
