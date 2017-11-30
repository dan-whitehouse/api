package org.ricone.api.core.request.validation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.AbstractController;
import org.ricone.api.core.request.validation.model.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "validation", description = "REST API for validation", tags = { "validation" })
public class ValidationController extends AbstractController
{
	@Autowired
    private IValidationService service;

    @ResponseBody
    @ApiOperation(value="Returns All Validation Information", tags = { "validation" })
    @RequestMapping(value= "/validation", method = RequestMethod.GET)
    public ValidationResponse getValidation(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        return service.findAll(getMetaData(pageRequest));
    }

    @ResponseBody
    @ApiOperation(value="Returns Validation Information by xLea refId", tags = { "validation" })
    @RequestMapping(value= "/validation/{refId}", method = RequestMethod.GET)
    public ValidationResponse getValidationByLea(HttpServletResponse response, Pageable pageRequest, @PathVariable(value="refId") String refId) throws Exception
    {
        return service.findAllByLea(getMetaData(pageRequest), refId);
    }
}