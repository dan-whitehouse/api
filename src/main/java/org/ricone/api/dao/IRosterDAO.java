package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.CourseSection;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterDAO
{
    List<CourseSection> findAll() throws NotFoundException;

    CourseSection findByRefId(String refId) throws Exception;

    void save(CourseSection lea);

    void update(CourseSection lea);

    void delete(CourseSection lea);

    void deleteByRefId(String refId);
}
