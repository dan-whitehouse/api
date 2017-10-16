package org.ricone.api.service;

import org.ricone.api.model.core.Staff;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IStaffService
{
    List<Staff> findAll(Pageable paging) throws Exception;

    List<Staff> findAllByLea(Pageable paging, String refId) throws Exception;

    List<Staff> findAllBySchool(Pageable paging, String refId) throws Exception;

    List<Staff> findAllByCourse(Pageable paging, String refId) throws Exception;

    List<Staff> findAllByRoster(Pageable paging, String refId) throws Exception;

    List<Staff> findAllByStudent(Pageable paging, String refId) throws Exception;

    Staff findByRefId(String refId) throws Exception;

    Staff findByLocalId(String localId) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);

    void deleteByRefId(String refId);
}
