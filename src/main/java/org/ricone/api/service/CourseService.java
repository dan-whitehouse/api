package org.ricone.api.service;

import org.ricone.api.dao.CourseDAO;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("courseService")
@Transactional
public class CourseService implements ICourseService
{
	@Autowired
	CourseDAO dao;

	@Override
	public List<Course> findAll() throws Exception {
		return dao.findAll();
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
