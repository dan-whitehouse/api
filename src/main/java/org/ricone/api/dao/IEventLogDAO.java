package org.ricone.api.dao;

import org.ricone.api.model.core.EventLog;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 10/26/2017.
 */
public interface IEventLogDAO {

    List<EventLog> findAllByLea(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllBySchool(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCalendar(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCourse(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByRoster(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStaff(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStudent(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByContact(Pageable pageRequest, LocalDateTime iso8601) throws Exception;
}
