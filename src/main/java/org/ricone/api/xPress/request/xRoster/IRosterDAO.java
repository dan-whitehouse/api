package org.ricone.api.xPress.request.xRoster;

import org.ricone.api.core.model.CourseSection;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterDAO
{
    List<CourseSectionWrapper> findAll(MetaData metaData) throws Exception;

    List<CourseSectionWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    CourseSectionWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);
}
