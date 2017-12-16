package org.ricone.api.xPress.request.xSchool;

import org.ricone.api.core.model.School;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.authentication.MetaData;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ISchoolService
{
    List<SchoolWrapper> findAll(MetaData metaData) throws Exception;

    List<SchoolWrapper> findAllByLea(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByCalendar(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception;

    List<SchoolWrapper> findAllByContact(MetaData metaData, String refId) throws Exception;

    SchoolWrapper findById(MetaData metaData, String refId) throws Exception;

    void save(School instance);

    void update(School instance);

    void delete(School instance);
}
