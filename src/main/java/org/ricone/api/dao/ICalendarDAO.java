package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.School;
import org.ricone.api.model.core.SchoolCalendar;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICalendarDAO
{
    List<SchoolCalendar> findAll() throws NotFoundException;

    SchoolCalendar findByRefId(String refId) throws Exception;

    void save(SchoolCalendar instance);

    void update(SchoolCalendar instance);

    void delete(SchoolCalendar instance);

    void deleteByRefId(String refId);
}
