package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.CourseSection;
import org.ricone.api.model.core.School;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterService
{
    List<CourseSection> findAll(Pageable pageRequest) throws Exception;

    List<CourseSection> findAllByLea(Pageable paging, String refId) throws Exception;

    List<CourseSection> findAllBySchool(Pageable paging, String refId) throws Exception;

    List<CourseSection> findAllByCourse(Pageable paging, String refId) throws Exception;

    List<CourseSection> findAllByStaff(Pageable paging, String refId) throws Exception;

    List<CourseSection> findAllByStudent(Pageable paging, String refId) throws Exception;

    CourseSection findByRefId(String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);

    void deleteByRefId(String refId);
}
