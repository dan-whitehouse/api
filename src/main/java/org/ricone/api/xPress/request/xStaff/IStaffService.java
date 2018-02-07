package org.ricone.api.xPress.request.xStaff;

import org.ricone.api.core.model.Staff;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;


public interface IStaffService {
    List<StaffWrapper> findAll(MetaData metaData) throws Exception;

    List<StaffWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<StaffWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception;

    StaffWrapper findById(MetaData metaData, String refId) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);
}
