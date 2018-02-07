package org.ricone.api.xPress.request.xStaff;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Staff;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.util.Util;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("StaffService")
@Transactional
public class StaffService implements IStaffService {
    @Autowired
    private IStaffDAO dao;

    @Override
    public List<StaffWrapper> findAll(MetaData metaData) throws Exception {
        return dao.findAll(metaData);
    }

    @Override
    public List<StaffWrapper> findAllByLea(MetaData metaData, String refId) throws Exception {
        return dao.findAllByLeaRefId(metaData, refId);
    }

    @Override
    public List<StaffWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception {
        return dao.findAllBySchoolRefId(metaData, refId);
    }

    @Override
    public List<StaffWrapper> findAllByCourse(MetaData metaData, String refId) throws Exception {
        return dao.findAllByCourseRefId(metaData, refId);
    }

    @Override
    public List<StaffWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception {
        return dao.findAllByRosterRefId(metaData, refId);
    }

    @Override
    public List<StaffWrapper> findAllByStudent(MetaData metaData, String refId) throws Exception {
        return dao.findAllByStudentRefId(metaData, refId);
    }

    @Override
    public StaffWrapper findById(MetaData metaData, String id) throws Exception {
        if(Util.isRefId(id)) {
            return dao.findByRefId(metaData, id);
        }
        else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "local")) {
            return dao.findByLocalId(metaData, id);
        }
        else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "state")) {
            return dao.findByStateId(metaData, id);
        }
        else {
            throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
        }
    }

    @Override
    public void save(Staff instance) {
        dao.save(instance);
    }

    @Override
    public void update(Staff instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Staff instance) {
        dao.delete(instance);
    }
}
