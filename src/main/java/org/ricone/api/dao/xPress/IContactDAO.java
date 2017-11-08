package org.ricone.api.dao.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.StudentContact;

import java.util.List;
import java.util.Set;

public interface IContactDAO
{
    List<StudentContact> findAll(MetaData metaData) throws Exception;

    List<StudentContact> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<StudentContact> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<StudentContact> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<StudentContact> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    StudentContact findByRefId(MetaData metaData, String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);
}
