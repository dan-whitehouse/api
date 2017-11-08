package org.ricone.api.dao.xPress;

import org.ricone.api.model.core.StudentIdentifier;

public interface IStudentIdentifierDAO
{
    int countLoginIdsBySchoolRefId(String refId, String staffRefId, String logInId) throws Exception;

    void save(StudentIdentifier instance);

    void update(StudentIdentifier instance);

    void delete(StudentIdentifier instance);
}
