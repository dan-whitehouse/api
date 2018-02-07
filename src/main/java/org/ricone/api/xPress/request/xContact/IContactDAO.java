package org.ricone.api.xPress.request.xContact;

import org.ricone.api.core.model.StudentContact;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

public interface IContactDAO {
    List<StudentContactWrapper> findAll(MetaData metaData) throws Exception;

    List<StudentContactWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<StudentContactWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<StudentContactWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<StudentContactWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    StudentContactWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(StudentContact instance);

    void update(StudentContact instance);

    void delete(StudentContact instance);
}
