package org.ricone.api.dao;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Lea;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ILeaDAO
{
    List<Lea> findAll(MetaData metaData) throws Exception;

    List<Lea> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findAllByContactRefId(MetaData metaData, String refId) throws Exception;

    List<Lea> findByRefIds(Set<String> refIds) throws Exception;

    Lea findByRefId(String refId) throws Exception;

    Lea findByLocalId(String localId) throws Exception;

    Lea findBySchoolRefId(String refId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    void deleteByRefId(String refId);

    Long count();
}
