package org.ricone.api.dao;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ILeaDAO
{
    List<Lea> findAll() throws NotFoundException;

    Lea findByRefId(String refId) throws NotFoundException;

    Lea findByLocalId(String localId) throws NotFoundException;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    void deleteByRefId(String refId);
}
