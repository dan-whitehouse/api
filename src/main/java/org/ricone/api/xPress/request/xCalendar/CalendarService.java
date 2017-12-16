package org.ricone.api.xPress.request.xCalendar;

import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.authentication.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("calendarService")
@Transactional
public class CalendarService implements ICalendarService
{
	@Autowired
	private ICalendarDAO dao;

	@Override
	public List<SchoolCalendarWrapper> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<SchoolCalendarWrapper> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<SchoolCalendarWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public SchoolCalendarWrapper findByRefId(MetaData metaData, String refId) throws Exception {
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
