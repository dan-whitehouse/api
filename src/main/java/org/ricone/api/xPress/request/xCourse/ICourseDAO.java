package org.ricone.api.xPress.request.xCourse;

import org.ricone.api.core.model.Course;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ICourseDAO
{
    List<Course> findAll(MetaData metaData) throws Exception;

    List<Course> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<Course> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<Course> findAllByRosterRefId(MetaData metaData, String refId) throws Exception;

    List<Course> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    Course findByRefId(MetaData metaData, String refId) throws Exception;

    void save(Course instance);

    void update(Course instance);

    void delete(Course instance);
}
