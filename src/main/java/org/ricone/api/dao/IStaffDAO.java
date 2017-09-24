package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Staff;

import java.util.List;

public interface IStaffDAO
{
    List<Staff> findAll() throws NotFoundException;

    Staff findByRefId(String refId) throws Exception;

    Staff findByLocalId(String localId) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);

    void deleteByRefId(String refId);
}
