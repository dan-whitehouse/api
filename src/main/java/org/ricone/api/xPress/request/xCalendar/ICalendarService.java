package org.ricone.api.xPress.request.xCalendar;

import org.ricone.api.core.dao.SchoolCalendar;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICalendarService
{
    List<SchoolCalendar> findAll(MetaData metaData) throws Exception;

    List<SchoolCalendar> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<SchoolCalendar> findAllBySchool(MetaData metaData, String refId) throws Exception;

    SchoolCalendar findByRefId(MetaData metaData, String refId) throws Exception;

    void save(SchoolCalendar instance);

    void update(SchoolCalendar instance);

    void delete(SchoolCalendar instance);
}
