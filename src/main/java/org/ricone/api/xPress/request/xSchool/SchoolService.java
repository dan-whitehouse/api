package org.ricone.api.xPress.request.xSchool;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.util.Util;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("schoolService")
@Transactional
public class SchoolService implements ISchoolService
{
	@Autowired
	private ISchoolDAO dao;

	@Override
	public List<SchoolWrapper> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<SchoolWrapper> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<SchoolWrapper> findAllByCalendar(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCalendarRefId(metaData, refId);
	}

	@Override
	public List<SchoolWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCourseRefId(metaData, refId);
	}

	@Override
	public List<SchoolWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception {
		return dao.findAllByRosterRefId(metaData, refId);
	}

	@Override
	public List<SchoolWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStaffRefId(metaData, refId);
	}

	@Override
	public List<SchoolWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public List<SchoolWrapper> findAllByContact(MetaData metaData, String refId) throws Exception {
		return dao.findAllByContactRefId(metaData, refId);
	}

	@Override
	public SchoolWrapper findById(MetaData metaData, String id) throws Exception {
		if (Util.isRefId(id)) {
			return dao.findByRefId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "local"))
		{
			return dao.findByLocalId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "BEDS"))
		{
			return dao.findByBEDSId(metaData, id);
		}
		else
		{
			throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
		}
	}

	@Override
	public void save(School instance) {
		dao.save(instance);
	}

	@Override
	public void update(School instance) {
		dao.update(instance);
	}

	@Override
	public void delete(School instance) {
		dao.delete(instance);
	}
}
