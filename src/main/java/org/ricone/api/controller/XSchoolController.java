package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.mapping.xPress.XSchoolMapper;
import org.ricone.api.model.core.School;
import org.ricone.api.model.xpress.*;
import org.ricone.api.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "xSchools", description = "REST API for xSchools", tags = { "xSchools" })
public class XSchoolController extends AbstractController
{
	@Autowired
    ISchoolService service;

    @Autowired
    XSchoolMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xSchool by refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xSchools/{refId}", method = RequestMethod.GET)
    public XSchoolResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        School instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xSchools", tags = { "xSchools" })
    @RequestMapping(value = "/requests/xSchools", method = RequestMethod.GET)
    public XSchoolsResponse getMulti(HttpServletResponse response) throws Exception
    {
        List<School> instance = service.findAll();
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xLeas by xSchools refId", tags = { "xSchools" })
    @RequestMapping(value= "/requests/xLeas/{refId}/xSchools", method = RequestMethod.GET)
    public List<XSchool> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}