package org.ricone.api.xPress.request.xLea;

import org.ricone.api.core.dao.Lea;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

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

    List<Lea> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    Lea findByRefId(MetaData metaData, String refId) throws Exception;

    Lea findByLocalId(MetaData metaData, String localId) throws Exception;

    Lea findBySchoolRefId(MetaData metaData, String refId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    Long count();
}
