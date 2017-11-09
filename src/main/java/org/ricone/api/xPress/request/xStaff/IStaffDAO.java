package org.ricone.api.xPress.request.xStaff;

import org.ricone.api.core.dao.Staff;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

public interface IStaffDAO
{
    List<Staff> findAll(MetaData metaData)throws Exception;

    List<Staff> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<Staff> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    Staff findByRefId(MetaData metaData, String refId) throws Exception;

    Staff findByLocalId(MetaData metaData, String localId) throws Exception;

    Staff findByStateId(MetaData metaData, String id) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);
}
