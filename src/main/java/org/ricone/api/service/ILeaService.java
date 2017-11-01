package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Lea;

import java.util.List;

public interface ILeaService
{
    List<Lea> findAll(MetaData metaData) throws Exception;

    List<Lea> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByCalendar(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByStudent(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByContact(MetaData metaData, String refId) throws Exception;

    Lea findById(MetaData metaData, String refId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);
}
