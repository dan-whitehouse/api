package org.ricone.api.service;

import org.ricone.api.dao.RosterDAO;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.CourseSection;
import org.ricone.api.model.core.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("rosterService")
@Transactional
public class RosterService implements IRosterService
{
	@Autowired
	RosterDAO dao;

	@Override
	public List<CourseSection> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public CourseSection findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
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

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
