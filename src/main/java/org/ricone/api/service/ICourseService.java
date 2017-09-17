package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.School;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICourseService
{
    List<Course> findAll() throws NotFoundException;

    Course findByRefId(String refId) throws Exception;

    void save(Course instance);

    void update(Course instance);

    void delete(Course instance);

    void deleteByRefId(String refId);
}
