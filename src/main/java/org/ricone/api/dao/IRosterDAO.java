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

    List<CourseSection> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception;

    List<CourseSection> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception;

    List<CourseSection> findAllByCourseRefId(Pageable pageRequest, String refId) throws Exception;

    List<CourseSection> findAllByStaffRefId(Pageable pageRequest, String refId) throws Exception;

    List<CourseSection> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception;

    CourseSection findByRefId(String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);

    void deleteByRefId(String refId);


}
