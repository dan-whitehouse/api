package org.ricone.api.xPress.request.xContact;

import org.ricone.api.core.model.StudentContact;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

public interface IContactService
{
    List<StudentContactWrapper> findAll(MetaData metaData) throws Exception;

    List<StudentContactWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<StudentContactWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<StudentContactWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception;

    StudentContactWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);
}
