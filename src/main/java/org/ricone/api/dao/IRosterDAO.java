package org.ricone.api.dao;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.CourseSection;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface IRosterDAO
{
    List<CourseSection> findAll(MetaData metaData) throws Exception;

    List<CourseSection> findAllByLeaRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllByCourseRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllByStaffRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findAllByStudentRefId(MetaData metaData, String refId) throws Exception;

    List<CourseSection> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception;

    CourseSection findByRefId(MetaData metaData, String refId) throws Exception;

    void save(CourseSection instance);

    void update(CourseSection instance);

    void delete(CourseSection instance);
}
