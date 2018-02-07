package org.ricone.api.xPress.request.xStudent;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.util.Util;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("StudentService")
@Transactional
public class StudentService implements IStudentService {
    @Autowired
    private IStudentDAO dao;

    @Override
    public List<StudentWrapper> findAll(MetaData metaData) throws Exception {
        return dao.findAll(metaData);
    }

    @Override
    public List<StudentWrapper> findAllByLea(MetaData metaData, String refId) throws Exception {
        return dao.findAllByLeaRefId(metaData, refId);
    }

    @Override
    public List<StudentWrapper> findAllBySchool(MetaData metaData, String refId) throws Exception {
        return dao.findAllBySchoolRefId(metaData, refId);
    }

    @Override
    public List<StudentWrapper> findAllByRoster(MetaData metaData, String refId) throws Exception {
        return dao.findAllByRosterRefId(metaData, refId);
    }

    @Override
    public List<StudentWrapper> findAllByStaff(MetaData metaData, String refId) throws Exception {
        return dao.findAllByStaffRefId(metaData, refId);
    }

    @Override
    public List<StudentWrapper> findAllByContact(MetaData metaData, String refId) throws Exception {
        return dao.findAllByContactRefId(metaData, refId);
    }

    @Override
    public StudentWrapper findById(MetaData metaData, String id) throws Exception {
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
    public void save(Student instance) {
        dao.save(instance);
    }

    @Override
    public void update(Student instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Student instance) {
        dao.delete(instance);
    }
}
