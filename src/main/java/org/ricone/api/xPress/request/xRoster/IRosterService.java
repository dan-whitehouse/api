package org.ricone.api.xPress.request.xRoster;

import org.ricone.api.core.model.CourseSection;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterService {
    List<CourseSectionWrapper> findAll(MetaData metaData) throws Exception;

    List<CourseSectionWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<CourseSectionWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception;

    CourseSectionWrapper findByRefId(MetaData metaData, String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);
}
