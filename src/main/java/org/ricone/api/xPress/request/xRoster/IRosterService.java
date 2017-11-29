package org.ricone.api.xPress.request.xRoster;

import org.ricone.api.core.model.CourseSection;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterService
{
    List<CourseSection> findAll(MetaData metaData) throws Exception;

    List<CourseSection> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllByStudent(MetaData metaData, String refId) throws Exception;

    CourseSection findByRefId(MetaData metaData, String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);
}
