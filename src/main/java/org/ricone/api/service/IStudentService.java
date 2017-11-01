package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Student;

import java.util.List;

public interface IStudentService
{
    List<Student> findAll(MetaData metaData) throws Exception;

    List<Student> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<Student> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<Student> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<Student> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<Student> findAllByContact(MetaData metaData, String refId) throws Exception;

    Student findById(MetaData metaData, String refId) throws Exception;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);
}
