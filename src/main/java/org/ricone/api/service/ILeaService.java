package org.ricone.api.service;

import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;

import java.util.List;

/**
 * @project: ricone
 * @author: Dan on 9/12/2017.
 */
public interface ILeaService
{
    List<Lea> findAll() throws Exception;

    Lea findByRefId(String refId) throws Exception;

    Lea findByLocalId(String localId) throws Exception;

    void save(Lea instance);

    void update(Lea instance);

    void delete(Lea instance);

    void deleteByRefId(String refId);
}
