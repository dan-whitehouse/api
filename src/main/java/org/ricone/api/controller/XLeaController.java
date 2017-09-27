package org.ricone.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ricone.api.exception.MappingException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.mapping.xPress.XLeaMapper;
import org.ricone.api.model.core.Lea;

import org.ricone.api.model.xpress.*;
import org.ricone.api.service.ILeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "xLeas", description = "REST API for xLeas", tags = { "xLeas" })
public class XLeaController extends AbstractController
{
	@Autowired
    ILeaService service;

    @Autowired
	XLeaMapper mapper;


    @ResponseBody
    @ApiOperation(value="Return xLea by refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xLeas/{refId}", method = RequestMethod.GET)
    public XLeaResponse getSingle(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        Lea instance = service.findByRefId(refId);
        return mapper.convert(instance);
    }

    @ResponseBody
    @ApiOperation(value="Return all xLeas", tags = { "xLeas" })
    @RequestMapping(value = "/requests/xLeas", method = RequestMethod.GET)
    public XLeasResponse getMultiPage(HttpServletResponse response, Pageable pageRequest) throws Exception
    {
        List<Lea> instance = service.findAll(pageRequest);
        return mapper.convert(instance);
    }


    @ResponseBody
    @ApiOperation(value="Return all xLeas by xSchool refId", tags = { "xLeas" })
    @RequestMapping(value= "/requests/xSchools/{refId}/xLeas", method = RequestMethod.GET)
    public List<XLea> getMultiByObject(HttpServletResponse response, @PathVariable(value="refId") String refId) throws Exception
    {
        return null;
    }
}