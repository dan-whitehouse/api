package org.ricone.api.xPress.request.xLea;

import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

public interface ILeaDAO {
    List<LeaWrapper> findAll(MetaData metaData) throws Exception;

    List<LeaWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByContactRefId(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    LeaWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    LeaWrapper findByLocalId(MetaData metaData, String localId) throws Exception;

    LeaWrapper findBySchoolRefId(MetaData metaData, String refId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    Long count();
}
