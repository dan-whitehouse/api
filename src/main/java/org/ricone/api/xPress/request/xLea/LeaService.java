package org.ricone.api.xPress.request.xLea;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.util.Util;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("leaService")
@Transactional
public class LeaService implements ILeaService {
    @Autowired
    private ILeaDAO dao;

    @Autowired
    private XLeaFilterer filterer;

    @Override
    public List<LeaWrapper> findAll(MetaData metaData) throws Exception {
        return dao.findAll(metaData);
    }

    @Override
    public List<LeaWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception {
        return dao.findAllBySchoolRefId(metaData, refId);
    }

    @Override
    public List<LeaWrapper> findAllByCalendar(MetaData metaData, String refId) throws Exception {
        return dao.findAllByCalendarRefId(metaData, refId);
    }

    @Override
    public List<LeaWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception {
        return dao.findAllByCourseRefId(metaData, refId);
    }

    @Override
    public List<LeaWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception {
        return dao.findAllByRosterRefId(metaData, refId);
    }

    @Override
    public List<LeaWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception {
        return dao.findAllByStaffRefId(metaData, refId);
    }

    @Override
    public List<LeaWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception {
        return dao.findAllByStudentRefId(metaData, refId);
    }

    @Override
    public List<LeaWrapper> findAllByContact(MetaData metaData, String refId) throws Exception {
        return dao.findAllByContactRefId(metaData, refId);
    }

    @Override
    public LeaWrapper findById(MetaData metaData, String id) throws Exception {
        if(Util.isRefId(id)) {
            return dao.findByRefId(metaData, id);
        }
        else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "local")) {
            return dao.findByLocalId(metaData, id);
        }
        else {
            throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
        }
    }

    @Override
    public void save(Lea instance) {
        dao.save(instance);
    }

    @Override
    public void update(Lea instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Lea instance) {
        dao.delete(instance);
    }
}
