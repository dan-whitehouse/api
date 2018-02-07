package org.ricone.api.xPress.request.xSchool;

import org.ricone.api.core.model.School;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ISchoolDAO {
    List<SchoolWrapper> findAll(MetaData metaData) throws Exception;

    List<SchoolWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByContactRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    SchoolWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    SchoolWrapper findByLocalId(MetaData metaData, String localId) throws Exception;

    SchoolWrapper findByBEDSId(MetaData metaData, String localId) throws Exception;

    void save(School lea);

    void update(School lea);

    void delete(School lea);
}
