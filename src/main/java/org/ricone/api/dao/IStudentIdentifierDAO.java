package org.ricone.api.dao;

import org.ricone.api.model.core.StudentIdentifier;

public interface IStudentIdentifierDAO
{
    void save(StudentIdentifier instance);

    void update(StudentIdentifier instance);

    void delete(StudentIdentifier instance);
}
