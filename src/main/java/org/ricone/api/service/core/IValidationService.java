package org.ricone.api.service.core;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.model.validation.ValidationResponse;

public interface IValidationService
{
    ValidationResponse findAll(MetaData metaData) throws Exception;

    ValidationResponse findAllByLea(MetaData metaData, String refId) throws Exception;
}
