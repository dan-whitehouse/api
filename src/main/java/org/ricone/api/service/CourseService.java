package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.CourseDAO;
import org.ricone.api.model.core.Course;
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
	public List<Course> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<Course> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<Course> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<Course> findAllByRoster(MetaData metaData, String refId) throws Exception {
		return dao.findAllByRosterRefId(metaData, refId);
	}

	@Override
	public Course findByRefId(MetaData metaData, String refId) throws Exception {
		return dao.findByRefId(metaData, refId);
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
}
