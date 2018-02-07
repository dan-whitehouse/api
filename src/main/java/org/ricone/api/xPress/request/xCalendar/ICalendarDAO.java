package org.ricone.api.xPress.request.xCalendar;

import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICalendarDAO {
    List<SchoolCalendarWrapper> findAll(MetaData metaData) throws Exception;

    List<SchoolCalendarWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolCalendarWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<SchoolCalendarWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    SchoolCalendarWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(SchoolCalendar instance);

    void update(SchoolCalendar instance);

    void delete(SchoolCalendar instance);
}
