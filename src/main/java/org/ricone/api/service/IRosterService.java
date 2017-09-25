package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.CourseSection;
import org.ricone.api.model.core.School;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterService
{
    List<CourseSection> findAll() throws Exception;

    CourseSection findByRefId(String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);

    void deleteByRefId(String refId);
}
