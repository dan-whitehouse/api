package org.ricone.api.service.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.xPress.RosterDAO;
import org.ricone.api.model.core.CourseSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("rosterService")
@Transactional
public class RosterService implements IRosterService
{
	@Autowired
	private RosterDAO dao;

	@Override
	public List<CourseSection> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<CourseSection> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<CourseSection> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<CourseSection> findAllByCourse(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCourseRefId(metaData, refId);
	}

	@Override
	public List<CourseSection> findAllByStaff(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStaffRefId(metaData, refId);
	}

	@Override
	public List<CourseSection> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public CourseSection findByRefId(MetaData metaData, String refId) throws Exception {
		return dao.findByRefId(metaData, refId);
	}

	@Override
	public void save(CourseSection instance) {
		dao.save(instance);
	}

	@Override
	public void update(CourseSection instance) {
		dao.update(instance);
	}

	@Override
	public void delete(CourseSection instance) {
		dao.delete(instance);
	}
}
