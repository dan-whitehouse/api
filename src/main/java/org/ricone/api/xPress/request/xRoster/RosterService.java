package org.ricone.api.xPress.request.xRoster;

import org.ricone.api.core.model.CourseSection;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.authentication.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("rosterService")
@Transactional
public class RosterService implements IRosterService
{
	@Autowired
	private IRosterDAO dao;

	@Override
	public List<CourseSectionWrapper> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<CourseSectionWrapper> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<CourseSectionWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<CourseSectionWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCourseRefId(metaData, refId);
	}

	@Override
	public List<CourseSectionWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStaffRefId(metaData, refId);
	}

	@Override
	public List<CourseSectionWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public CourseSectionWrapper findByRefId(MetaData metaData, String refId) throws Exception {
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
