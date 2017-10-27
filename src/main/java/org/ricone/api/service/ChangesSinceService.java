package org.ricone.api.service;

import org.ricone.api.dao.IChangesSinceDAO;
import org.ricone.api.model.core.EventLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service("changesSinceService")
@Transactional
public class ChangesSinceService implements IChangesSinceService
{
	@Autowired
	IChangesSinceDAO dao;


	@Override
	public List<EventLog> findAllByLea(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByLea(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllBySchool(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllBySchool(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllByCalendar(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByCalendar(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllByCourse(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByCourse(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllByRoster(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByRoster(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllByStaff(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByStaff(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllByStudent(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByStudent(pageRequest, iso8601);
	}

	@Override
	public List<EventLog> findAllByContact(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		return dao.findAllByContact(pageRequest, iso8601);
	}
}
