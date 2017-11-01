package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Staff;

import java.util.List;


public interface IStaffService
{
    List<Staff> findAll(MetaData metaData) throws Exception;

    List<Staff> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<Staff> findAllByStudent(MetaData metaData, String refId) throws Exception;

    Staff findById(MetaData metaData, String refId) throws Exception;

    void save(Staff instance);

    void update(Staff instance);

    void delete(Staff instance);
}
