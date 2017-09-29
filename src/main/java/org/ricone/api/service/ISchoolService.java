package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ISchoolService
{
    List<School> findAll(Pageable pageRequest) throws Exception;

    List<School> findAllByLea(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByCalendar(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByCourse(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByRoster(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByStaff(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByStudent(Pageable pageRequest, String refId) throws Exception;

    List<School> findAllByContact(Pageable pageRequest, String refId) throws Exception;

    School findById(HttpServletRequest request, String refId) throws Exception;

    void save(School instance);

    void update(School instance);

    void delete(School instance);

    void deleteByRefId(String refId);
}
