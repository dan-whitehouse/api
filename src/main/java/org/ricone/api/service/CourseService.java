package org.ricone.api.service;

import org.ricone.api.dao.CourseDAO;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Service("courseService")
@Transactional
public class CourseService implements ICourseService
{
	@Autowired
	CourseDAO dao;

	@Override
	public List<Course> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public List<Course> findAllByLea(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByLeaRefId(pageRequest, refId);
	}

	@Override
	public List<Course> findAllBySchool(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllBySchoolRefId(pageRequest, refId);
	}

	@Override
	public List<Course> findAllByRoster(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByRosterRefId(pageRequest, refId);
	}

	@Override
	public Course findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public void save(Course instance) {
		dao.save(instance);
	}

	@Override
	public void update(Course instance) {
		dao.update(instance);
	}

	@Override
	public void delete(Course instance) {
		dao.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
