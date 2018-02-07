package org.ricone.api.core.request.validation;

import org.ricone.api.core.request.validation.model.ValidationResponse;
import org.ricone.authentication.MetaData;

public interface IValidationService {
    ValidationResponse findAll(MetaData metaData) throws Exception;

    ValidationResponse findAllByLea(MetaData metaData, String refId) throws Exception;
}
