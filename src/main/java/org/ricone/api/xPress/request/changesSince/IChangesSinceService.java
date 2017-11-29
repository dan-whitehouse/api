package org.ricone.api.xPress.request.changesSince;

import org.ricone.api.core.model.EventLog;
import org.ricone.authentication.MetaData;

import java.time.LocalDateTime;
import java.util.List;

public interface IChangesSinceService
{
    List<EventLog> findAllByLea(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllBySchool(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCalendar(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCourse(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByRoster(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStaff(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStudent(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByContact(MetaData metaData, LocalDateTime iso8601) throws Exception;
}
