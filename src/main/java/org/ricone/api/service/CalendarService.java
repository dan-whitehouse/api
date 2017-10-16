package org.ricone.api.service;

import org.ricone.api.dao.CalendarDAO;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.School;
import org.ricone.api.model.core.SchoolCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("calendarService")
@Transactional
public class CalendarService implements ICalendarService
{
	@Autowired
	CalendarDAO dao;

	@Override
	public List<SchoolCalendar> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public List<SchoolCalendar> findAllByLea(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByLeaRefId(pageRequest, refId);
	}

	@Override
	public List<SchoolCalendar> findAllBySchool(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllBySchoolRefId(pageRequest, refId);
	}

	@Override
	public SchoolCalendar findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public void save(SchoolCalendar instance) {
		dao.save(instance);
	}

	@Override
	public void update(SchoolCalendar instance) {
		dao.update(instance);
	}

	@Override
	public void delete(SchoolCalendar instance) {
		dao.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
