package org.ricone.api.xPress.request.xLea;

import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

public interface ILeaService
{
    List<LeaWrapper> findAll(MetaData metaData) throws Exception;

    List<LeaWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByCalendar(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception;

    List<LeaWrapper> findAllByContact(MetaData metaData, String refId) throws Exception;

    LeaWrapper findById(MetaData metaData, String refId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);
}
