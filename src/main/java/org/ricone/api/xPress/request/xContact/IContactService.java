package org.ricone.api.xPress.request.xContact;

import org.ricone.api.core.dao.StudentContact;
import org.ricone.authentication.MetaData;

import java.util.List;

public interface IContactService
{
    List<StudentContact> findAll(MetaData metaData) throws Exception;

    List<StudentContact> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<StudentContact> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<StudentContact> findAllByStudent(MetaData metaData, String refId) throws Exception;

    StudentContact findByRefId(MetaData metaData, String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);
}
