package org.ricone.api.dao;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Student;

import java.util.List;
import java.util.Set;

public interface IStudentDAO
{
    List<Student> findAll(MetaData metaData) throws Exception;

    List<Student> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<Student> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<Student> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<Student> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<Student> findAllByContactRefId(MetaData metaData, String refId) throws Exception;

    List<Student> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    Student findByRefId(MetaData metaData, String refId) throws Exception;

    Student findByLocalId(MetaData metaData, String localId) throws Exception;

    Student findByStateId(MetaData metaData, String id) throws Exception;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);
}
