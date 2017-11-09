package org.ricone.api.xPress.request.aupp;

import org.ricone.api.core.dao.StaffIdentifier;

public interface IStaffIdentifierDAO
{
    int countLoginIdsBySchoolRefId(String refId, String staffRefId, String logInId) throws Exception;

    void save(StaffIdentifier instance);

    void update(StaffIdentifier instance);

    void delete(StaffIdentifier instance);
}
