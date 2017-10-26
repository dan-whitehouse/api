package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStaffDAO
{
    List<Staff> findAll(Pageable paging)throws Exception;

    List<Staff> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception;

    List<Staff> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception;

    List<Staff> findAllByCourseRefId(Pageable pageRequest, String refId) throws Exception;

    List<Staff> findAllByRosterRefId(Pageable pageRequest, String refId) throws Exception;

    List<Staff> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception;

    Staff findByRefId(String refId) throws Exception;

    Staff findByLocalId(String localId) throws Exception;

    Staff findByStateId(String id) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);

    void deleteByRefId(String refId);
}
