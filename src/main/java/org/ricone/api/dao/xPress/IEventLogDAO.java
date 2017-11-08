package org.ricone.api.dao.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.EventLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 10/26/2017.
 */
public interface IEventLogDAO {

    List<EventLog> findAllByLea(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllBySchool(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCalendar(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCourse(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByRoster(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStaff(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStudent(MetaData metaData, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByContact(MetaData metaData, LocalDateTime iso8601) throws Exception;
}
