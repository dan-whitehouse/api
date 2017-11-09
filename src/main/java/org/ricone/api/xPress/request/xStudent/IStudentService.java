package org.ricone.api.xPress.request.xStudent;

import org.ricone.api.core.dao.Student;
import org.ricone.authentication.MetaData;

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
