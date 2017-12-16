package org.ricone.api.xPress.request.xCourse;

import org.ricone.api.core.model.Course;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICourseDAO
{
    List<CourseWrapper> findAll(MetaData metaData) throws Exception;

    List<CourseWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<CourseWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<CourseWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<CourseWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    CourseWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(Course instance);

    void update(Course instance);

    void delete(Course instance);
}
