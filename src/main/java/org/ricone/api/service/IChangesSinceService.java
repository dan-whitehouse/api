package org.ricone.api.service;

import org.ricone.api.model.core.EventLog;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IChangesSinceService
{
    List<EventLog> findAllByLea(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllBySchool(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCalendar(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByCourse(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByRoster(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStaff(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByStudent(Pageable pageRequest, LocalDateTime iso8601) throws Exception;

    List<EventLog> findAllByContact(Pageable pageRequest, LocalDateTime iso8601) throws Exception;
}
