package org.ricone.api.xPress.request.xSchool;

import org.ricone.api.core.model.School;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ISchoolService
{
    List<School> findAll(MetaData metaData) throws Exception;

    List<School> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<School> findAllByCalendar(MetaData metaData, String refId) throws Exception;

    List<School> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<School> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<School> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<School> findAllByStudent(MetaData metaData, String refId) throws Exception;

    List<School> findAllByContact(MetaData metaData, String refId) throws Exception;

    School findById(MetaData metaData, String refId) throws Exception;

    void save(School instance);

    void update(School instance);

    void delete(School instance);
}
