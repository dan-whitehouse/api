package org.ricone.api.xPress.request.xCourse;

import org.ricone.api.core.model.Course;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICourseService
{
    List<CourseWrapper> findAll(MetaData metaData) throws Exception;

    List<CourseWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<CourseWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<CourseWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception;

    CourseWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(Course instance);

    void update(Course instance);

    void delete(Course instance);
}
