package org.ricone.api.dao;

import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.core.UserPassword;
import org.ricone.api.model.core.extension.userpassword.EntityType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userPasswordDAO")
public class UserPasswordDAO extends AbstractDAO<Integer, UserPassword> implements IUserPasswordDAO {
    public boolean provisionStaffsBySchool(List<Staff> staffs) throws Exception {

        staffs.forEach(staff -> {
            UserPassword userPassword = new UserPassword();
            userPassword.setEntityRefId(staff.getStaffRefId());
            userPassword.setEntityType(EntityType.STAFF);
            userPassword.setAppId("Test");
            //userPassword.setExpiryDate();
            //userPassword.setTempPassword();
            save(userPassword);
        });

        return false;
    }

    public boolean deleteStaffsBySchool(Pageable pageRequest, String refId) throws Exception {
        return false;
    }

    public List<Staff> findStaffsBySchool(Pageable pageRequest, String refId) throws Exception {
        return null;
    }

    public boolean provisionStudentsBySchool(Pageable pageRequest, String refId) throws Exception {
        return false;
    }

    public boolean deleteStudentsBySchool(Pageable pageRequest, String refId) throws Exception {
        return false;
    }

    public List<Student> findStudentsBySchool(Pageable pageRequest, String refId) throws Exception {
        return null;
    }

    @Override
    public void save(UserPassword instance) {
        super.persist(instance);
    }

    @Override
    public void update(UserPassword instance) {
        super.saveOrUpdate(instance);
    }

    @Override
    public void delete(UserPassword instance) {
        super.delete(instance);
    }
}
