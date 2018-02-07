package org.ricone.api.xPress.request.xCalendar;

import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICalendarService {
    List<SchoolCalendarWrapper> findAll(MetaData metaData) throws Exception;

    List<SchoolCalendarWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<SchoolCalendarWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    SchoolCalendarWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(SchoolCalendar instance);

    void update(SchoolCalendar instance);

    void delete(SchoolCalendar instance);
}
