package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;

import java.util.List;

public interface IStudentDAO
{
    List<Student> findAll() throws NotFoundException;

    Student findByRefId(String refId) throws NotFoundException;

    Student findByLocalId(String localId) throws NotFoundException;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);

    void deleteByRefId(String refId);
}