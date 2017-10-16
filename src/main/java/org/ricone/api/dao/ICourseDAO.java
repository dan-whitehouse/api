package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.School;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICourseDAO
{
    List<Course> findAll(Pageable pageRequest) throws Exception;

    Course findByRefId(String refId) throws Exception;

    void save(Course instance);

    void update(Course instance);

    void delete(Course instance);

    void deleteByRefId(String refId);
}
