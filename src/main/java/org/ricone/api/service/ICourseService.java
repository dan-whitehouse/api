package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.School;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICourseService
{
    List<Course> findAll(Pageable pageRequest) throws Exception;

    List<Course> findAllByLea(Pageable pageRequest, String refId) throws Exception;

    List<Course> findAllBySchool(Pageable pageRequest, String refId) throws Exception;

    List<Course> findAllByRoster(Pageable pageRequest, String refId) throws Exception;

    Course findByRefId(String refId) throws Exception;

    void save(Course instance);

    void update(Course instance);

    void delete(Course instance);

    void deleteByRefId(String refId);
}
