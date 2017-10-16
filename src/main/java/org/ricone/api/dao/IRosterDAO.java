package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.CourseSection;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterDAO
{
    List<CourseSection> findAll(Pageable pageRequest) throws Exception;

    CourseSection findByRefId(String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);

    void deleteByRefId(String refId);
}
