package org.ricone.api.dao;

import org.ricone.api.model.core.StaffIdentifier;

public interface IStaffIdentifierDAO
{
    void save(StaffIdentifier instance);

    void update(StaffIdentifier instance);

    void delete(StaffIdentifier instance);
}
