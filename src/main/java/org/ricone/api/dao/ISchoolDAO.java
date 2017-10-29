package org.ricone.api.dao;

import org.ricone.api.model.core.School;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ISchoolDAO
{
    List<School> findAll(Pageable pageRequest) throws Exception;

    List<School> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByCalendarRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByCourseRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByRosterRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByStaffRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByContactRefId(Pageable pageRequest, String refId) throws Exception;

    List<School> findByRefIds(Set<String> refIds) throws Exception;

    School findByRefId(String refId) throws Exception;

    School findByLocalId(String localId) throws Exception;

    School findByBEDSId(String localId) throws Exception;

    void save(School lea);

    void update(School lea);

    void delete(School lea);

    void deleteByRefId(String refId);
}
