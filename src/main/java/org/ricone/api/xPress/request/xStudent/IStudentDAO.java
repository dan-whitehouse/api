package org.ricone.api.xPress.request.xStudent;

import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

public interface IStudentDAO {
    List<StudentWrapper> findAll(MetaData metaData) throws Exception;

    List<StudentWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findAllByContactRefId(MetaData metaData, String refId) throws Exception;

    List<StudentWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    StudentWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    StudentWrapper findByLocalId(MetaData metaData, String localId) throws Exception;

    StudentWrapper findByStateId(MetaData metaData, String id) throws Exception;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);
}
