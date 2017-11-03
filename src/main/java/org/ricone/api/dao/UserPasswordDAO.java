package org.ricone.api.dao;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.core.Staff;
import org.ricone.api.model.core.Student;
import org.ricone.api.model.core.UserPassword;
import org.ricone.api.model.core.extension.userpassword.EntityType;
import org.ricone.api.util.AES;
import org.ricone.api.util.UserPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("userPasswordDAO")
public class UserPasswordDAO extends AbstractDAO<Integer, UserPassword> implements IUserPasswordDAO {

    @Autowired
    UserPasswordGenerator generator;

    @Autowired
    AES security;

    public boolean provisionStaffsBySchool(MetaData metaData, HashMap<String, String> kv, List<Staff> staffs) throws Exception {
        staffs.forEach(staff -> {
            Date now = new Date();
            long ttl = (long)(Double.parseDouble(kv.get("api.userpass.password.ttl")) * 86400000);
            String password = generator.getPassword(kv, staff, metaData.getApp().getId());
            String key = security.getRefToKey(staff.getStaffRefId());

            //TODO - EntityType doesn't insert correctly. Hibernate Bug: https://hibernate.atlassian.net/browse/HHH-10594
            UserPassword userPassword = new UserPassword();
            userPassword.setEntityRefId(staff.getStaffRefId());
            userPassword.setEntityType(EntityType.STAFF);
            userPassword.setAppId(metaData.getApp().getId());
            userPassword.setTempPassword(security.encrypt(password, key));

            userPassword.setGenerationDate(now);
            userPassword.setExpiryDate(new Date(ttl + now.getTime()));
            userPassword.setLastRetrieved(null);
            update(userPassword);
        });

        return false;
    }

    public boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception {
        return false;
    }

    public List<Staff> findStaffsBySchool(MetaData metaData, String refId) throws Exception {
        return null;
    }

    public boolean provisionStudentsBySchool(MetaData metaData, String refId) throws Exception {
        return false;
    }

    public boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {
        return false;
    }

    public List<Student> findStudentsBySchool(MetaData metaData, String refId) throws Exception {
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
