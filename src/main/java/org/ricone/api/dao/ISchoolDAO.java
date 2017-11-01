package org.ricone.api.dao;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.School;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ISchoolDAO
{
    List<School> findAll(MetaData metaData) throws Exception;

    List<School> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<School> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception;

    List<School> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<School> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<School> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<School> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<School> findAllByContactRefId(MetaData metaData, String refId) throws Exception;

    List<School> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    School findByRefId(MetaData metaData, String refId) throws Exception;

    School findByLocalId(MetaData metaData, String localId) throws Exception;

    School findByBEDSId(MetaData metaData, String localId) throws Exception;

    void save(School lea);

    void update(School lea);

    void delete(School lea);
}
