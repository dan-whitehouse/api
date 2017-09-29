package org.ricone.api.dao;

import org.ricone.api.exception.NoContentException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ILeaDAO
{
    List<Lea> findAll(Pageable pageRequest) throws Exception;

    List<Lea> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByCalendarRefId(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByCourseRefId(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByRosterRefId(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByStaffRefId(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByStudentRefId(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByContactRefId(Pageable pageRequest, String refId) throws Exception;

    Lea findByRefId(String refId) throws Exception;

    Lea findByLocalId(String localId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    void deleteByRefId(String refId);

    Long count();
}
