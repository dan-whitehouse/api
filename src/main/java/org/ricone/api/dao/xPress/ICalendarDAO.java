package org.ricone.api.dao.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.SchoolCalendar;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICalendarDAO
{
    List<SchoolCalendar> findAll(MetaData metaData) throws Exception;

    List<SchoolCalendar> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolCalendar> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolCalendar> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    SchoolCalendar findByRefId(MetaData metaData, String refId) throws Exception;

    void save(SchoolCalendar instance);

    void update(SchoolCalendar instance);

    void delete(SchoolCalendar instance);
}
