package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Staff;

import java.util.List;


public interface IStaffService
{
    List<Staff> findAll() throws Exception;

    Staff findByRefId(String refId) throws Exception;

    Staff findByLocalId(String localId) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);

    void deleteByRefId(String refId);
}
