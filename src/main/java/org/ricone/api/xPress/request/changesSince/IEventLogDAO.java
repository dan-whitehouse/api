package org.ricone.api.xPress.request.changesSince;

import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.authentication.MetaData;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 10/26/2017.
 */
public interface IEventLogDAO {

    List<EventLogWrapper> findAllByLea(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllBySchool(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllByCalendar(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllByCourse(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllByRoster(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllByStaff(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllByStudent(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLogWrapper> findAllByContact(MetaData metaData, LocalDateTime iso8601) throws Exception;
}
