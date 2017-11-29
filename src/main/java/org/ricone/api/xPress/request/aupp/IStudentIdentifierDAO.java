package org.ricone.api.xPress.request.aupp;

import org.ricone.api.core.model.StudentIdentifier;

public interface IStudentIdentifierDAO
{
    int countLoginIdsBySchoolRefId(String refId, String staffRefId, String logInId) throws Exception;

    void save(StudentIdentifier instance);

    void update(StudentIdentifier instance);

    void delete(StudentIdentifier instance);
}
