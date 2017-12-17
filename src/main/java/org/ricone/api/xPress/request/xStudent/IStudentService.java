package org.ricone.api.xPress.request.xStudent;

import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

public interface IStudentService
{
    List<StudentWrapper> findAll(MetaData metaData) throws Exception;

    List<StudentWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllByContact(MetaData metaData, String refId) throws Exception;

    StudentWrapper findById(MetaData metaData, String refId) throws Exception;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);
}
