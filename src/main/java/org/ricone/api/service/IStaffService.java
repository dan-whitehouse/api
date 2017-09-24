package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Staff;

import java.util.List;


public interface IStaffService
{
    List<Staff> findAll() throws NotFoundException;

    Staff findByRefId(String refId) throws NotFoundException;

    Staff findByLocalId(String localId) throws NotFoundException;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);

    void deleteByRefId(String refId);
}
