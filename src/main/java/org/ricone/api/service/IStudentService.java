package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IStudentService
{
    List<Student> findAll(Pageable pageRequest) throws Exception;

    List<Student> findAllByLea(Pageable paging, String refId) throws Exception;

    List<Student> findAllBySchool(Pageable paging, String refId) throws Exception;

    List<Student> findAllByRoster(Pageable paging, String refId) throws Exception;

    List<Student> findAllByStaff(Pageable paging, String refId) throws Exception;

    List<Student> findAllByContact(Pageable paging, String refId) throws Exception;

    Student findById(HttpServletRequest request, String refId) throws Exception;

    void save(Student instance);

    void update(Student instance);

    void delete(Student instance);

    void deleteByRefId(String refId);


}
