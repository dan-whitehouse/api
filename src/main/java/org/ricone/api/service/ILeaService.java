package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ILeaService
{
    List<Lea> findAll(Pageable pageRequest) throws Exception;

    List<Lea> findAllBySchool(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByCalendar(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByCourse(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByRoster(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByStaff(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByStudent(Pageable pageRequest, String refId) throws Exception;

    List<Lea> findAllByContact(Pageable pageRequest, String refId) throws Exception;

    Lea findById(String refId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    void deleteByRefId(String refId);
}
