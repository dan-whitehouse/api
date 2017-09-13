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

    Lea findByRefId(String refId) throws Exception;

    Lea findByLocalId(String localId) throws Exception;

    void save(Lea lea);

    void update(Lea lea);

    void delete(Lea lea);

    void deleteByRefId(String refId);
}
