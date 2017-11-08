package org.ricone.api.service.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.StudentContact;

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
