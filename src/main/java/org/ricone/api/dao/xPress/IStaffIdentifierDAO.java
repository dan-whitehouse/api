package org.ricone.api.dao.xPress;

import org.ricone.api.model.core.StaffIdentifier;

public interface IStaffIdentifierDAO
{
    int countLoginIdsBySchoolRefId(String refId, String staffRefId, String logInId) throws Exception;

    void save(StaffIdentifier instance);

    void update(StaffIdentifier instance);

    void delete(StaffIdentifier instance);
}
