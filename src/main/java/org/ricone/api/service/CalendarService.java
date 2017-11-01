package org.ricone.api.service;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.CalendarDAO;
import org.ricone.api.model.core.SchoolCalendar;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<SchoolCalendar> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<SchoolCalendar> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<SchoolCalendar> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public SchoolCalendar findByRefId(MetaData metaData, String refId) throws Exception {
		return dao.findByRefId(metaData, refId);
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
}
