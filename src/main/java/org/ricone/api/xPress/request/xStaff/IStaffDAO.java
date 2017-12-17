package org.ricone.api.xPress.request.xStaff;

import org.ricone.api.core.model.Staff;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

public interface IStaffDAO
{
    List<StaffWrapper> findAll(MetaData metaData)throws Exception;

    List<StaffWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    StaffWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    StaffWrapper findByLocalId(MetaData metaData, String localId) throws Exception;

    StaffWrapper findByStateId(MetaData metaData, String id) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);
}
